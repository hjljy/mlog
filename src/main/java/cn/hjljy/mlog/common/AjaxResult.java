package cn.hjljy.mlog.common;

import cn.hjljy.mlog.common.constants.Constant;
import lombok.Data;

/**
 * @author 海加尔金鹰 www.hjljy.cn
 * @version V1.0
 * @email hjljy@outlook.com
 * @description: 前端返回对象
 * @since 2020/1/11 1:08
 **/

@Data
public class AjaxResult<T> {
    private int code;
    private String msg;
    private T data;

    public AjaxResult (){
        setCode(Constant.SUCCESS_CODE);
        setMsg(Constant.OP_SUCCEED);
    }
    public AjaxResult (int code,String msg){
        setCode(code);
        setMsg(msg);
    }
    public static AjaxResult Fail(){
        return new AjaxResult(Constant.FAIL_CODE,Constant.OP_FAIL);
    }
    public static AjaxResult Fail(String msg){
        return new AjaxResult(Constant.FAIL_CODE,msg);
    }
    public static AjaxResult Fail(int code,String msg){
        return new AjaxResult(code,msg);
    }

    public static AjaxResult Success(Object data) {
        AjaxResult result = new AjaxResult();
        result.setData(data);
        return result;
    }
    public static AjaxResult SUCCESS(){
        return new AjaxResult();
    }
}
