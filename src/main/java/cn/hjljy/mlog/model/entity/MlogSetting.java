package cn.hjljy.mlog.model.entity;

import java.io.Serializable;
import java.util.List;

import cn.hjljy.mlog.model.enums.SettingOptionKeyEnum;
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
    private SettingOptionKeyEnum optionKey;

    /**
     * 具体值
     */
    private String optionValue;


    /**
     *
     * 获取到对应optionKey的值否则取默认值
     *
     * @param optionKey   key
     * @param settingList 设置列表
     * @return 值
     */
    public static String getValueByKeyOrDefault(SettingOptionKeyEnum optionKey, List<MlogSetting> settingList) {
        return settingList.stream().filter(item -> item.getOptionKey().equals(optionKey)).findFirst().map(MlogSetting::getOptionValue).orElse(optionKey.getDefaultValue());
    }

    public String getValueOrDefault() {
        String value = this.getOptionValue();
        if(null==value){
            return this.optionKey.getDefaultValue();
        }
        return value;
    }
}
