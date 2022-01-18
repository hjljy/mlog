package cn.hjljy.mlog.config.interceptor;


import cn.hjljy.mlog.common.support.ResultCode;
import cn.hjljy.mlog.common.utils.HttpServletRequestUtils;
import cn.hjljy.mlog.common.utils.TokenUtils;
import cn.hutool.core.util.StrUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.invoke.MethodHandle;
import java.nio.charset.StandardCharsets;

/**
 * mlog身份验证配置拦截器
 *
 * @author 海加尔金鹰 www.hjljy.cn
 * @version V1.2
 * @email hjljy@outlook.com
 * Mlog项目后台权限拦截器
 * @date 2021/10/14
 */
public class AuthConfigInterceptor implements HandlerInterceptor {

    @Autowired
    TokenUtils tokenUtils;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        //如果不是映射到方法的请求，直接放行，避免静态资源被拦截
        if(!(handler instanceof MethodHandle)){
            return true;
        }
        //在调用getWriter之前设置编码，否者不生效
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String authorization = HttpServletRequestUtils.getAuthorization(request);
        //如果没有携带token
        if (StrUtil.isBlank(authorization)) {
            HttpServletRequestUtils.buildBadResponseInfo(request, response, HttpStatus.UNAUTHORIZED, ResultCode.TOKEN_NOT_FOUND,"/401.html");
            return false;
        }
        if (null == tokenUtils.get(authorization)) {
            HttpServletRequestUtils.buildBadResponseInfo(request, response, HttpStatus.UNAUTHORIZED,ResultCode.TOKEN_INVALID,"/401.html");
            return false;
        }
        return true;
    }


}
