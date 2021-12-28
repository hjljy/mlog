package cn.hjljy.mlog.common.enums;

/**
 * 状态枚举
 *
 * @author hjljy
 * @date 2021/12/21
 */
public enum ArticleStatusEnum {
    /**
     * 发布
     */
    RELEASE(1),
    /**
     * 草稿 (默认值)
     */
    DRAFT(2);

    private int code;
    ArticleStatusEnum(int code) {
        this.code=code;
    }

    public int getCode() {
        return code;
    }
}
