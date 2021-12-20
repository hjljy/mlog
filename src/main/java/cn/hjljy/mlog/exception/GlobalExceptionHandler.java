package cn.hjljy.mlog.exception;

import cn.hjljy.mlog.common.ResultCode;
import cn.hjljy.mlog.common.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 全局异常处理程序
 *
 * @author 海加尔金鹰 www.hjljy.cn
 * @email hjljy@outlook.com
 * @description: 全局controller代码异常处理类
 * @date 2021/10/08
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理请求方法不匹配异常
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultInfo<Object> errorHandler(HttpRequestMethodNotSupportedException ex) {
        return ResultInfo.error(ResultCode.REQUEST_METHOD_EXCEPTION.getCode(), ex.getMessage());
    }

    /**
     * 处理请求方法参数格式
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultInfo<Object> errorHandler() {
        return ResultInfo.error(ResultCode.PARAMETERS_EXCEPTION);
    }

    /**
     * 处理参数类型异常信息
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResultInfo<Object> errorHandler(IllegalArgumentException ex) {
        return ResultInfo.error(ResultCode.PARAMETERS_EXCEPTION.getCode(), ex.getMessage());
    }


    /**
     * 处理方法参数数据不符合要求异常信息
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultInfo<Object> errorHandler(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        ResultInfo<Object> resultInfo = ResultInfo.error(ResultCode.PARAMETERS_EXCEPTION);
        List<ObjectError> errors = result.getAllErrors();
        List<String> msg = new ArrayList<>();
        for (ObjectError error : errors) {
            msg.add(error.getDefaultMessage());
        }
        resultInfo.setMsg(msg.toString());
        return resultInfo;
    }

    /**
     * 处理业务抛出的异常信息
     */
    @ExceptionHandler(value = MlogException.class)
    public ResultInfo<Object> errorHandler(MlogException ex) {
        return ResultInfo.error(ex.getCode(), ex.getMessage());
    }

    /**
     * 处理其他的所有异常信息
     */
    @ExceptionHandler(value = Exception.class)
    public ResultInfo<Object> errorHandler(Exception ex) {
        ex.printStackTrace();
        ResultInfo<Object> resultInfo = ResultInfo.error(ResultCode.ERROR);
        if (ex instanceof SQLException) {
            resultInfo.setCode(ResultCode.SQL_EXCEPTION.getCode());
        } else if (ex instanceof NullPointerException) {
            resultInfo.setCode(ResultCode.NPE_EXCEPTION.getCode());
        }
        return resultInfo;
    }
}
