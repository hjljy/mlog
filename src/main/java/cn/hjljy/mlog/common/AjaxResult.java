package cn.hjljy.mlog.common;

import cn.hjljy.mlog.common.constants.Constant;
import cn.hjljy.mlog.common.constants.ResultCode;
import lombok.Data;

/**
 * @author 海加尔金鹰 www.hjljy.cn
 * @version V1.0
 * @email hjljy@outlook.com
 * @description: 前端返回对象
 * @since 2020/1/11 1:08
 **/

@Data
public class AjaxResult {
    private ResultCode code;
    private String msg;
    private Object data;

    public AjaxResult (){
        setCode(ResultCode.OK);
        setMsg(Constant.OP_SUCCEED);
    }
    public AjaxResult (ResultCode code,String msg){
        setCode(code);
        setMsg(msg);
    }
    public static AjaxResult Fail(String msg){
        return new AjaxResult(ResultCode.FAIL,msg);
    }
    public static AjaxResult Fail(ResultCode code,String msg){
        return new AjaxResult(code,msg);
    }
}
