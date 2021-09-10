package cn.hjljy.mlog.common;

/**
 * @author hjljy
 * @date 2020/6/4 16:57
 * @apiNote 前端AJAX请求返回实体
 */
public class ResultInfo<T> {
    private int code;

    private String msg;

    private T data;

    public ResultInfo(T data) {
        this.code = ResultCode.SUCCESS.getCode();
        this.msg = ResultCode.SUCCESS.getMsg();
        this.data = data;
    }

    public ResultInfo(int code, String msg) {
        this.code = code;
        this.msg = msg;

    }

    public static <T> ResultInfo<T> error(String msg) {
        ResultInfo<T> resultInfo = error(ResultCode.DEFAULT);
        resultInfo.setMsg(msg);
        return resultInfo;
    }

    public static <T> ResultInfo<T> error(ResultCode code) {
        return new ResultInfo<>(code.getCode(), code.getMsg());
    }

    public static <T> ResultInfo<T> success(T data) {
        return new ResultInfo<>(data);
    }

    public static <T> ResultInfo<T> success() {
        return new ResultInfo<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
    }

    public static <T> ResultInfo<T> error(int code, String message) {
        return new ResultInfo<>(code, message);
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
