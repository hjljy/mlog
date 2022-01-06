package cn.hjljy.mlog.model.enums;

import lombok.Getter;

/**
 * 设置选项键枚举
 *
 * @author hjljy
 * @date 2021/12/31
 */
@Getter
public enum SettingOptionKeyEnum {
    /**
     * 文件存储
     */
    FILE_STORAGE("fileStorage","{\"type\":\"local\"}");

    /**
     * optionKey的值
     */
    private final String optionKey;
    private final String defaultValue;
    SettingOptionKeyEnum(String optionKey, String defaultValue) {
        this.optionKey=optionKey;
        this.defaultValue=defaultValue;
    }
}
