package cn.hjljy.mlog.controller;

import cn.hjljy.mlog.entity.MlogArticlesEntity;
import cn.hjljy.mlog.entity.MlogConfigEntity;
import cn.hjljy.mlog.service.IMlogArticlesService;
import cn.hjljy.mlog.service.IMlogConfigService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 海加尔金鹰 www.hjljy.cn
 * @version V1.0
 * @since 2020/1/16 21:21
 * 首页界面
 **/
@Controller
public class IndexController {
    @Autowired
    IMlogConfigService configService;


    @Autowired
    IMlogArticlesService mlogArticlesService;
    @GetMapping
    public String index(Model model) {
        Map<String, Object> indexVo = new HashMap<>();
        //获取博客系统的皮肤信息
        QueryWrapper<MlogConfigEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(MlogConfigEntity::getConfigName, "defaultSkin").eq(MlogConfigEntity::getConfigType, "skin");
        MlogConfigEntity skinConfig = configService.getOne(queryWrapper);
        indexVo.put("themePath", skinConfig != null ? skinConfig.getConfigValue() : "/themes/layuiSimpleBlog/html");
        QueryWrapper<MlogConfigEntity> queryTitle = new QueryWrapper<>();
        queryTitle.lambda().eq(MlogConfigEntity::getConfigName, "blogTitle").eq(MlogConfigEntity::getConfigType, "base");
        MlogConfigEntity titleConfig = configService.getOne(queryTitle);
        indexVo.put("blogTitle", titleConfig != null ? titleConfig.getConfigValue() : "/themes/layuiSimpleBlog/html");
        model.addAllAttributes(indexVo);
        return "/themes/layuiSimpleBlog/html/index";
    }

    @GetMapping("/index")
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("/");
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable long id,Model model) {
        MlogArticlesEntity articlesEntity = mlogArticlesService.getById(id);
        return "/themes/layuiSimpleBlog/html/details";
    }
}
