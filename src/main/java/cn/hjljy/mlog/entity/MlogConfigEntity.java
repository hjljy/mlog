package cn.hjljy.mlog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2020-01-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mlog_config")
public class MlogConfigEntity implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 配置项名称
     */
    @TableId("configName")
    private String configName;

    /**
     * 配置项值
     */
    @TableField("configValue")
    private String configValue;

    /**
     * 配置项分类
     */
    @TableField("configType")
    private String configType;


    public MlogConfigEntity(String local) {
        this.configValue=local;
    }
}
