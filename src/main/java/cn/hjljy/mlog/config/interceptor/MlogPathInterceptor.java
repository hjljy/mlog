package cn.hjljy.mlog.config.interceptor;

import cn.hjljy.mlog.common.constants.Constant;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 海加尔金鹰 www.hjljy.cn
 * @version V1.0
 * @email hjljy@outlook.com
 * @description: mlog路径拦截器
 * @since 2020/3/9 0:38
 **/
@Slf4j
public class MlogPathInterceptor implements HandlerInterceptor {


//    @Autowired
//    IMlogArticlesService mlogArticlesService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //获取所有文章的URL
//        List<String> allUrl = mlogArticlesService.getAllUrl();
//        //获取请求的路径
//        String servletPath = request.getServletPath();
//        //如果包含就跳转
//        if(allUrl.contains(servletPath)){
//            QueryWrapper<MlogArticlesEntity> queryWrapper =new QueryWrapper<>();
//            queryWrapper.lambda().eq(MlogArticlesEntity::getArticleUrl,servletPath);
//            MlogArticlesEntity one = mlogArticlesService.getOne(queryWrapper);
//            //将本次请求发送到/details
//            request.getRequestDispatcher("/details/"+one.getAuthorId()).forward(request,response);
//            return false;
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String servletPath = request.getRequestURI();
        int status = response.getStatus();
        if(HttpStatus.NOT_FOUND.value()==status&&!Constant.ERROR_PATH.equals(servletPath)){
            log.warn("请求地址:{}不存在",servletPath);
        }

    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
