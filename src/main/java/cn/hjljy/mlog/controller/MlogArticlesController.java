package cn.hjljy.mlog.controller;


import cn.hjljy.mlog.common.AjaxResult;
import cn.hjljy.mlog.common.utils.HttpServletRequestUtils;
import cn.hjljy.mlog.entity.MlogArticlesEntity;
import cn.hjljy.mlog.entity.MlogUserEntity;
import cn.hjljy.mlog.service.IMlogArticlesService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2020-01-16
 */
@RestController
@RequestMapping("/article")
public class MlogArticlesController {

    @Autowired
    IMlogArticlesService mlogArticlesService;

    /**
     * markdown 文章导入
     * @param file 文章
     * @param request 请求
     * @return
     */
    @PostMapping("/import")
    public AjaxResult importMd(@RequestParam("file") MultipartFile[] files, HttpServletRequest request){

            MlogArticlesEntity articlesEntity = new MlogArticlesEntity();
            try {
                for (MultipartFile file : files) {
                    InputStream inputStream = file.getInputStream();
                    String fileContent = IOUtils.toString(inputStream, null);
                    String setting = StringUtils.substringBefore(fileContent, "---");
                    if(StringUtils.isBlank(setting)){
                        StringUtils.substringAfter("", "---");
                        StringUtils.substringBefore("", "---");
                    }
                }
      /*      MlogUserEntity userInfo = HttpServletRequestUtils.getUserInfoByHttpRequest(request);
            articlesEntity.setAuthor(userInfo.getUsername());
            articlesEntity.setArticleUrl(userInfo.getAvatarUrl());
            mlogArticlesService.save(articlesEntity);*/
            } catch (IOException e) {
                e.printStackTrace();
            }
        return AjaxResult.Fail("导入markdown文章失败");
    }
}

