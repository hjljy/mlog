package cn.hjljy.mlog.controller;

import cn.hjljy.mlog.common.AjaxResult;
import cn.hjljy.mlog.entity.MlogUserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 海加尔金鹰 www.hjljy.cn
 * @version V1.0
 * @email hjljy@outlook.com
 * @description: 后台界面的跳转
 * @since 2020/1/22 11:35
 **/
@Controller
@RequestMapping("/mlog")
public class AdminController {

    @GetMapping("/index")
    public String index() {
        return "mlog/index";
    }

    @GetMapping("/article")
    public String article() {
        return "mlog/article";
    }

    @GetMapping("/comment")
    public String comment() {
        return "mlog/comment";
    }

    @GetMapping("/publish")
    public String articlePublish() {
        return "mlog/publish";
    }

}
