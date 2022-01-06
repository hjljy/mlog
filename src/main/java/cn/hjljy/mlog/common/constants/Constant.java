package cn.hjljy.mlog.common.constants;

/**
 * 常数
 *
 * @author hjljy
 * @date 2021/09/10
 */
public class Constant {

    /**
     * request header token 标识
     */
    public static final String TOKEN_HEAD ="token";

    /**
     * request header ajax标识
     */
    public static final String AJAX_HEARD ="x-requested-with";

    /**
     * request header ajax标识的值
     */
    public static final String AJAX_HEARD_VALUE ="XMLHttpRequest";

    /**
     * 上传文件前缀信息
     */
    public static final String UPLOAD_PREFIX ="files/upload/";

    /**
     * 错误页面地址
     */
    public static final String ERROR_PATH ="/error";

    /**
     * 逗号分割值
     */
    public static final String CSV=",";

    /**
     * 默认颜色
     */
    public static final String DEFAULT_COLOR="#FFFFFF";
    public static final int FAIL_CODE=1000;
    public static final int SUCCESS_CODE=0;
    /**
     * 数据获取失败
     */
    public static final String OP_FAIL = "操作失败";

    /**
     * 数据获取失败
     */
    public static final String OP_SUCCEED = "操作成功";




}
