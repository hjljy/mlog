package cn.hjljy.mlog.model.entity;

import java.io.Serializable;
import java.util.List;

import cn.hjljy.mlog.model.enums.SettingTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2021-10-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MlogSetting implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 设置类型
     */
    private SettingTypeEnum type;

    /**
     * 设置的key
     */
    private String optionKey;

    /**
     * 具体值
     */
    private String optionValue;


    public static String getValueByKey(String optionKey, List<MlogSetting> settingList) {
        return getValueByKeyOrDefault(optionKey, settingList, null);
    }

    public static String getValueByKeyOrDefault(String optionKey, List<MlogSetting> settingList, String defaultValue) {
        return settingList.stream().filter(item -> item.getOptionKey().equals(optionKey)).findFirst().map(MlogSetting::getOptionValue).orElse(defaultValue);
    }
}
