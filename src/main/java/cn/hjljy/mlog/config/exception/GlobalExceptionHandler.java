package cn.hjljy.mlog.config.exception;

import cn.hjljy.mlog.common.AjaxResult;
import cn.hjljy.mlog.common.utils.HttpServletRequestUtils;
import cn.hutool.log.Log;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 海加尔金鹰 www.hjljy.cn
 * @version V1.0
 * @email hjljy@outlook.com
 * @description: 全局controller代码异常处理类
 * @since 2020/2/8 20:15
 **/
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private Log logger =Log.get();

    @ExceptionHandler(MlogException.class)
    public Object doAuthorizationException(MlogException  e , HttpServletRequest request){
        logger.error(e.getMessage(),e);
        if(HttpServletRequestUtils.isAjax(request)){
            return  AjaxResult.Fail("没有操作权限");
        }
        return  "redirect:/403.html";
    }
    @ExceptionHandler
    public Object doExceptionHandler(Exception e, HttpServletRequest request){
        logger.error(e.getMessage(),e);
        if(HttpServletRequestUtils.isAjax(request)){
            return  AjaxResult.Fail("服务器内部错误！！！");
        }
        return  new ModelAndView("/error");
    }
}
