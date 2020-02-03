package cn.hjljy.mlog.common.utils;

import cn.hjljy.mlog.entity.MlogArticlesEntity;
import cn.hjljy.mlog.entity.MlogUserEntity;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 获取当前请求的用户信息
     *
     * @param request 请求
     * @return
     */
    public static MlogUserEntity getUserInfoByHttpRequest(HttpServletRequest request) {
        return  (MlogUserEntity) request.getSession().getAttribute("user");
    }
}
