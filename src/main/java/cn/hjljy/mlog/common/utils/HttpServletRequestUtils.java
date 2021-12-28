package cn.hjljy.mlog.common.utils;

import cn.hjljy.mlog.common.ResultCode;
import cn.hjljy.mlog.common.ResultInfo;
import cn.hjljy.mlog.common.constants.Constant;
import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 海加尔金鹰 www.hjljy.cn
 * @version V1.0
 * @email hjljy@outlook.com
 * @description: httpServletRequest工具类
 * @since 2020/1/23 20:58
 **/
public class HttpServletRequestUtils {
    public static String getCookieByName(HttpServletRequest httpServletRequest, String name) {
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().toLowerCase().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 获取授权 优先获取请求头当中的Authorization  如果没有在获取cookie当中的token
     *
     * @param request http servlet请求
     * @return {@link String}
     */
    public static String getAuthorization(HttpServletRequest request) {
        //优先取请求头当中的
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StrUtil.isBlank(token)) {
            token = getCookieByName(request, Constant.TOKEN_HEAD);
        }
        return token;
    }

    /**
     * 判断是否为ajax请求，默认不是
     *
     * @param req 请求
     * @return boolean  default false
     */
    public static boolean isAjax(HttpServletRequest req) {
        boolean isAjaxRequest = false;
        //如果包含约定的ajax请求头x-requested-with
        if (!StringUtils.isBlank(req.getHeader(Constant.AJAX_HEARD)) && req.getHeader(Constant.AJAX_HEARD).equals(Constant.AJAX_HEARD_VALUE)) {
            isAjaxRequest = true;
        }
        //或者请求的content_type是application/json
        if (!StringUtils.isBlank(req.getContentType())&&req.getContentType().contains(MediaType.APPLICATION_JSON_VALUE)) {
            isAjaxRequest = true;
        }
        return isAjaxRequest;
    }

    public static void buildBadResponseInfo(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull HttpStatus status, @NonNull ResultCode resultCode,@NonNull String redirectUrl) throws IOException {
        response.setStatus(status.value());
        if (isAjax(request)) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            PrintWriter writer = response.getWriter();
            ResultInfo<Boolean> errorInfo = ResultInfo.error(resultCode);
            errorInfo.setData(false);
            writer.write(errorInfo.toString());
        } else {
            response.sendRedirect(redirectUrl);
        }
    }
}
