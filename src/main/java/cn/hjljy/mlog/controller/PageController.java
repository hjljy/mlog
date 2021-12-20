package cn.hjljy.mlog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 页面控制器
 *
 * @author hjljy
 * @date 2021/10/08
 */
@Controller
@RequestMapping
public class PageController {
    /**
     * 登录页面跳转
     *
     * @return templates/login.ftl
     */
    @GetMapping("login")
    public String login() {
        ModelAndView mv = new ModelAndView();
        return "/login";
    }

    /**
     * 后台首页
     *
     * @return templates/admin/index.ftl
     */
    @GetMapping("/admin/index")
    public String index() {
        return "admin/index";
    }
    @GetMapping("/article")
    public String article() {
        return "admin/article";
    }
    @GetMapping("/comment")
    public String comment() {
        return "admin/comment";
    }
    @GetMapping("/publish")
    public String articlePublish() {
        return "admin/publish";
    }
}
