package cn.hjljy.mlog.common.constants;

/**
 * @author 海加尔金鹰 www.hjljy.cn
 * @version V1.0
 * @email hjljy@outlook.com
 * @description: 返回码
 * @since 2020/1/11 1:13
 **/
public enum  ResultCode {
    FAIL(-1,"失败"),
    OK(0,"成功"),
    SYSFAIL(1000,"系统代码错误"),
    DDFail(2000,"数据库操作失败");

    private int code;
    private String desc;

    ResultCode(int code, String desc) {
        this.code=code;
        this.desc=desc;
    }
}
