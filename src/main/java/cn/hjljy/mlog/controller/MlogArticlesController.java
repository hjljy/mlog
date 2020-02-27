package cn.hjljy.mlog.controller;


import cn.hjljy.mlog.common.AjaxResult;
import cn.hjljy.mlog.common.utils.HttpServletRequestUtils;
import cn.hjljy.mlog.entity.MlogArticlesEntity;
import cn.hjljy.mlog.entity.MlogUserEntity;
import cn.hjljy.mlog.service.IMlogArticlesService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.beans.factory.config.YamlProcessor;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.boot.json.YamlJsonParser;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.Yaml;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2020-01-16
 */
@RestController
@RequestMapping("/mlog/article")
public class MlogArticlesController extends BaseController {

    @Autowired
    IMlogArticlesService mlogArticlesService;

    /**
     * 分页列表
     *
     * @param pageSize
     * @param pageNumber
     * @param keywords
     * @return
     */
    @GetMapping("/list")
    public AjaxResult list(int pageSize, int pageNumber, String keywords) {
        Page<MlogArticlesEntity> page = mlogArticlesService.pageList(pageSize, pageNumber, keywords);
        return AjaxResult.SUCCESS(page);
    }

    /**
     * 保存，更新文章
     *
     * @param entity
     * @return
     */
    @PostMapping("/save")
    public AjaxResult save(@RequestBody MlogArticlesEntity entity) {
        if(entity.getId()!=null){
            mlogArticlesService.updateArticle(entity);
        }else {
            entity.setCreateTime(System.currentTimeMillis());
            if(StringUtils.isEmpty(entity.getArticleUrl())){
                String format = DateUtil.format(new Date(), "yyyy/MM/dd");
                entity.setArticleUrl("/articles/"+format +"/"+System.currentTimeMillis()+".html");
            }
            mlogArticlesService.saveArticle(entity);
        }
        return AjaxResult.SUCCESS(entity);
    }

    /**
     * markdown 文章批量导入
     *
     * @param file    文章
     * @param request 请求
     * @return
     */
    @PostMapping("/import")
    public AjaxResult importMd(@RequestParam("files") MultipartFile[] files, HttpServletRequest request) {

        //获取当前用户信息
        MlogUserEntity userInfo = HttpServletRequestUtils.getUserInfoByHttpRequest(request);
        for (MultipartFile file : files) {
            //解析md生成文章对象
            MlogArticlesEntity articlesEntity = null;
            try {
                articlesEntity = parseMdFile(file);
            } catch (IOException e) {
                log.error("导入markdown文章失败：{}", e.getMessage());
                return AjaxResult.Fail("导入markdown文章失败：" + e.getMessage());
            }
            articlesEntity.setAuthorId(userInfo.getId());
            articlesEntity.setStatus(0);
            articlesEntity.setOntop(1);
            articlesEntity.setType(3);
            articlesEntity.setCommentable(0);
            mlogArticlesService.saveArticle(articlesEntity);
        }
        return AjaxResult.SUCCESS();
    }

    /**
     * 导出所有文章为MD
     *
     * @param response 响应
     */
    @GetMapping("/export")
    public void exportedMd(HttpServletResponse response) {
        List<MlogArticlesEntity> list = mlogArticlesService.list();
        //生成zip文件存放位置
        long timeMillis = System.currentTimeMillis();
        String strZipPath = "D:/markdowm/" + timeMillis + ".zip";
        File file = new File("D:/markdowm/");
        //文件存放位置目录不存在就创建
        if (!file.isDirectory() && !file.exists()) {
            file.mkdirs();
        }
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(strZipPath));
            for (int i = 0; i < list.size(); i++) {
                MlogArticlesEntity entity = list.get(i);
                out.putNextEntry(new ZipEntry(entity.getTitle() + ".md"));
                int len;
                StringBuffer header = new StringBuffer();
                header.append("title: " + entity.getTitle() + "\n");
                header.append("date: " + DateUtil.format(new Date(entity.getCreateTime()), "yyyy-MM-dd hh:mm:ss") + "\n");
                header.append("updated: " + DateUtil.format(new Date(entity.getCreateTime()), "yyyy-MM-dd hh:mm:ss") + "\n");
                header.append("tags: " + Arrays.asList(entity.getTags().split(",")).toString() + "\n");
                header.append("---\n");
                // 读入需要下载的文件的内容，打包到zip文件
                out.write(entity.getContent().getBytes());
                out.closeEntry();
            }
            out.close();
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(strZipPath));
            FileCopyUtils.copy(bis, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        removeDir(file);
    }

    /**
     * 解析MD文件
     *
     * @param file md文件
     * @return 返回文章对象
     */
    private MlogArticlesEntity parseMdFile(MultipartFile file) throws IOException {
        //解析MD文件头部内容
        InputStream inputStream = file.getInputStream();
        String fileContent = IOUtils.toString(inputStream, null);
        inputStream.close();
        String setting = StringUtils.substringBefore(fileContent, "---");
        if (StringUtils.isBlank(setting)) {
            fileContent = StringUtils.substringAfter(fileContent, "---");
            setting = StringUtils.substringBefore(fileContent, "---");
        }
        fileContent = StringUtils.substringAfter(fileContent, "---");
        Yaml yam = new Yaml();
        Map<String, Object> map = yam.load(setting);
        //根据解析文件构建文章对象
        MlogArticlesEntity articlesEntity = new MlogArticlesEntity();
        articlesEntity.setAbstractText(fileContent.substring(0, 100));
        articlesEntity.setContent(fileContent);
        if (map.get("title") == null) {
            articlesEntity.setTitle(map.get("title").toString());
        } else {
            articlesEntity.setTitle(StringUtils.substringBefore(file.getOriginalFilename(), ".md"));
        }
        if (map.get("tags") != null) {
            //处理tags
            String tags = map.get("tags").toString();
            if (map.get("tags") instanceof ArrayList) {
                boolean first = true;
                tags = "";
                ArrayList<String> tagList = (ArrayList) map.get("tags");
                for (String tag : tagList) {
                    if (first) {
                        first = false;
                    } else {
                        tags += ",";
                    }
                    tags += tag;
                }
            }
            articlesEntity.setTags(tags);
        }
        if (map.get("permalink") != null) {
            articlesEntity.setArticleUrl(map.get("permalink").toString());
        }else {
            String format = DateUtil.format(new Date(), "yyyy/MM/dd");
            articlesEntity.setArticleUrl("/articles/"+format +"/"+System.currentTimeMillis()+".html");
        }
        if (map.get("date") != null) {
            articlesEntity.setCreateTime(DateUtil.parseDateTime(map.get("date").toString()).getTime());
        }else {
            articlesEntity.setCreateTime(System.currentTimeMillis());
        }
        if (map.get("updated") != null) {
            articlesEntity.setUpdateTime(DateUtil.parseDateTime(map.get("updated").toString()).getTime());
        }
        return articlesEntity;
    }

    /**
     * 删除文件或者文件夹下所有文件
     *
     * @param dir
     */
    private void removeDir(File dir) {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                removeDir(file);
            } else {
                file.delete();
            }
        }
    }
}

