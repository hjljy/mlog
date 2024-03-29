package cn.hjljy.mlog.common.support;

/**
 * @author hjljy
 * @date 2020/6/4 16:57
 * @apiNote 前端AJAX请求返回码以及含义
 */
public enum ResultCode {
    //统一返回
    SUCCESS(0, "操作成功"),
    DEFAULT(1, "操作失败"),
    ERROR(2, "服务器异常,请联系管理员"),
    NOT_FOUND(404, "接口地址不正确"),


    //业务逻辑异常
    NOT_ALLOW(10000,"不允许本次操作"),
    PARAMETERS_EXCEPTION(10001,"请求参数错误"),
    REQUEST_METHOD_EXCEPTION(10002,"请求方式错误"),

    DATA_EXIST(11000,"数据已存在,请勿重复添加"),
    DATA_NOT_EXIST(11001,"数据不存在"),


    //用户 权限相关异常
    USER_NOT_FOUND(20001,"用户不存在"),
    USER_DISABLE(20002,"用户被禁用,请联系管理员"),
    USER_EXIST(20003,"用户账号已存在"),
    USER_PASSWORD_WRONG(20004,"用户名或密码错误"),

    OAUTH2_INVALID(21000,"权限或登录异常，请联系管理员"),
    PERMISSION_DENIED(21001,"权限不足，请联系管理员"),

    TOKEN_EXPIRED(22001,"TOKEN已过期"),
    TOKEN_NOT_FOUND(22002,"未携带TOKEN,非法请求!"),
    TOKEN_INVALID(22003,"无效TOKEN"),


    INVALID_CLIENT(22011,"无效客户端, 请检查客户端参数"),
    INVALID_SCOPE(22012,"无效SCOPE值, 请检查授权范围是否正确"),
    UNSUPPORTED_GRANT_TYPE(22013,"不支持的授权模式"),


    //
    FILE_STORAGE_NOT_EXISTS(40000,"文件存储方式不存在"),
    MD_FILE_IMPORT(40001,"Markdown文件导入失败"),
    //服务端代码异常
    SQL_EXCEPTION(90000,"服务器异常,请联系管理员"),
    NPE_EXCEPTION(90001,"服务器异常,请联系管理员");


    private int code;

    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
