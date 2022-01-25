package cn.hjljy.mlog.controller.admin;


import cn.hjljy.mlog.common.support.ResultInfo;
import cn.hjljy.mlog.model.dto.ArticleDTO;
import cn.hjljy.mlog.model.params.ArticleParams;
import cn.hjljy.mlog.model.params.ArticleQuery;
import cn.hjljy.mlog.service.IMlogArticleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 文章管理
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2021-12-17
 */
@RestController
@RequestMapping("/admin/article")
@AllArgsConstructor
public class MlogArticleController {

    private final IMlogArticleService articleService;


    /**
     * 分页查询文章信息
     *
     * @param query 查询
     * @return {@link ResultInfo}<{@link IPage}<{@link ArticleDTO}>>
     */
    @PostMapping("/page")
    public ResultInfo<IPage<ArticleDTO>> page(@RequestBody ArticleQuery query) {
        IPage<ArticleDTO> page = articleService.pageByQuery(query.buildPage(ArticleDTO.class), query);
        return ResultInfo.success(page);
    }

    /**
     * 通过id查询文章
     *
     * @param id id
     * @return {@link ResultInfo}<{@link ArticleDTO}>
     */
    @GetMapping("/{id}")
    public ResultInfo<ArticleDTO> getById(@PathVariable Long id) {
        ArticleDTO dto = articleService.getDetailById(id);
        return ResultInfo.success(dto);
    }

    /**
     * 通过id删除文章
     *
     * @param id id
     * @return {@link ResultInfo}<{@link ArticleDTO}>
     */
    @DeleteMapping("/{id}")
    public ResultInfo<Boolean> del(@PathVariable Long id) {
        return ResultInfo.success(articleService.removeById(id));
    }

    /**
     * 发布文章
     *
     * @param dto dto
     * @return {@link ResultInfo}<{@link Boolean}>
     */
    @PostMapping()
    public ResultInfo<Boolean> publish(@RequestBody @Validated ArticleDTO dto) {
        return ResultInfo.success(articleService.publish(dto));
    }

    /**
     * 更新文章评论状态和置顶状态
     *
     * @param params dto 文章实体类
     * @return {@link ResultInfo}<{@link Boolean}>
     */
    @PutMapping()
    public ResultInfo<Boolean> update(@RequestBody @Validated ArticleParams params) {
        if (null != params.getTop()) {
            articleService.updateArticleTop(params.getArticleId(), params.getTop());
        }
        if (null != params.getDisallowComment()) {
            articleService.updateArticleDisallowComment(params.getArticleId(), params.getDisallowComment());
        }
        return ResultInfo.success();
    }

    /**
     * markdown 文章批量导入
     *
     * @param files   文章
     * @param request 请求
     * @return 是否成功
     */
    @PostMapping("/importMd")
    public ResultInfo<Boolean> importMd(@RequestParam("files") MultipartFile[] files, HttpServletRequest request) {
        return ResultInfo.success(articleService.importMd(files, request));
    }

//    /**
//     * 导出所有文章为MD
//     *
//     * @param response 响应
//     */
//    @GetMapping("/export")
//    public void exportedMd(HttpServletResponse response) {
//        List<MlogArticlesEntity> list = mlogArticlesService.list();
//        //生成zip文件存放位置
//        long timeMillis = System.currentTimeMillis();
//        String strZipPath = "D:/markdowm/" + timeMillis + ".zip";
//        File file = new File("D:/markdowm/");
//        //文件存放位置目录不存在就创建
//        if (!file.isDirectory() && !file.exists()) {
//            file.mkdirs();
//        }
//        try {
//            ServletOutputStream outputStream = response.getOutputStream();
//            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(strZipPath));
//            for (int i = 0; i < list.size(); i++) {
//                MlogArticlesEntity entity = list.get(i);
//                out.putNextEntry(new ZipEntry(entity.getTitle() + ".md"));
//                int len;
//                StringBuffer header = new StringBuffer();
//                header.append("title: " + entity.getTitle() + "\n");
//                header.append("date: " + DateUtil.format(new Date(entity.getCreateTime()), "yyyy-MM-dd hh:mm:ss") + "\n");
//                header.append("updated: " + DateUtil.format(new Date(entity.getCreateTime()), "yyyy-MM-dd hh:mm:ss") + "\n");
//                header.append("tags: " + Arrays.asList(entity.getTags().split(",")).toString() + "\n");
//                header.append("---\n");
//                // 读入需要下载的文件的内容，打包到zip文件
//                out.write(entity.getContent().getBytes());
//                out.closeEntry();
//            }
//            out.close();
//            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(strZipPath));
//            FileCopyUtils.copy(bis, outputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        removeDir(file);
//    }


}

