package cn.hjljy.mlog.config.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 海加尔金鹰 www.hjljy.cn
 * @version V1.0
 * @email hjljy@outlook.com
 * @description: 路径映射错误处理
 * @since 2020/2/8 20:30
 **/
@Controller
public class MainsiteErrorHandler  implements ErrorController {
    private static final String ERROR_PATH = "/error";

    @RequestMapping(ERROR_PATH)
    public String handleError(HttpServletResponse response) {
        // 获取到响应状态码
        int status = response.getStatus();
        if (status == 401) {
            return "redirect:/login.html";
        } else if (status == 403) {
            return "redirect:/403.html";
        } else if (status == 404) {
            return "redirect:/404.html";
        } else {
            return "redirect:/500.html";
        }
    }
//
//    @Override
//    public String getErrorPath() {
//        return ERROR_PATH;
//    }
}
