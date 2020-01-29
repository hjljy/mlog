package cn.hjljy.mlog.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 海加尔金鹰 www.hjljy.cn
 * @version V1.0
 * @email hjljy@outlook.com
 * @description: 工具类
 * @since 2020/1/23 20:58
 **/
public class HttpServletRequestUtils {
    public static String getCookieByName(HttpServletRequest httpServletRequest, String username) {
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(username)){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
