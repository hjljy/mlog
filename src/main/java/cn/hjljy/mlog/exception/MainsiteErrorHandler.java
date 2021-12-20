package cn.hjljy.mlog.exception;

import cn.hjljy.mlog.common.ResultCode;
import cn.hjljy.mlog.common.utils.HttpServletRequestUtils;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public void handleError(HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 获取到响应状态码
        int status = response.getStatus();
        if (status == HttpStatus.UNAUTHORIZED.value()) {
            HttpServletRequestUtils.buildBadResponseInfo(request, response, HttpStatus.UNAUTHORIZED, ResultCode.TOKEN_NOT_FOUND,"/401.html");
        }else if (status == HttpStatus.NOT_FOUND.value()) {
            HttpServletRequestUtils.buildBadResponseInfo(request, response, HttpStatus.NOT_FOUND,ResultCode.NOT_FOUND,"/404.html");
        } else {
            HttpServletRequestUtils.buildBadResponseInfo(request, response, HttpStatus.INTERNAL_SERVER_ERROR,ResultCode.ERROR,"/500.html");
        }
    }
}
