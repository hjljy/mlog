package cn.hjljy.mlog.exception;

import cn.hjljy.mlog.common.ResultCode;

/**
 * @author 海加尔金鹰 www.hjljy.cn
 * @version V1.0
 * @email hjljy@outlook.com
 * @description: Mlog自定义异常
 * @since 2021/12/8 21:46
 **/
public class MlogException extends RuntimeException {

    private int code;

    public MlogException(ResultCode code, String msg) {
        super(msg);
        this.code = code.getCode();
    }
    public MlogException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
