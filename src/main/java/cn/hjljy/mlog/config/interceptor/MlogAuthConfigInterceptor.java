package cn.hjljy.mlog.config.interceptor;

import cn.hjljy.mlog.common.utils.HttpServletRequestUtils;
import cn.hjljy.mlog.entity.MlogUserEntity;
import cn.hjljy.mlog.service.IMlogUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 海加尔金鹰 www.hjljy.cn
 * @version V1.0
 * @email hjljy@outlook.com
 * @description: Mlog项目后台权限拦截器
 * @since 2020/1/23 20:42
 **/
public class MlogAuthConfigInterceptor implements HandlerInterceptor {

    @Autowired
    IMlogUserService mlogUserService;

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if (HttpServletRequestUtils.getUserInfoByHttpRequest(httpServletRequest) != null) {
            return;
        }
        String username = HttpServletRequestUtils.getCookieByName(httpServletRequest, "username");
        if (username == null) {
            httpServletResponse.sendRedirect("/login.html");
            return;
        }
        String email = HttpServletRequestUtils.getCookieByName(httpServletRequest, "email");
        if (email == null) {
            httpServletResponse.sendRedirect("/login.html");
            return;
        }
        QueryWrapper<MlogUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(MlogUserEntity::getEmail, email)
                .eq(MlogUserEntity::getUsername, username)
                .in(MlogUserEntity::getRoleId, 0, 1);
        MlogUserEntity userEntity = mlogUserService.getOne(queryWrapper);
        if (userEntity == null) {
            httpServletResponse.sendRedirect("/login.html");
        } else {
            httpServletRequest.getSession().setAttribute("user", userEntity);
        }
    }
}
