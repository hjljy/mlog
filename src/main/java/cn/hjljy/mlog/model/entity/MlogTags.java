package cn.hjljy.mlog.model.entity;

import cn.hjljy.mlog.common.valid.Update;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2021-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MlogTags implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(message = "id不能为空",groups = Update.class)
    private Long id;

    /**
     * 标签
     */
    @TableField(value= "tag")
    @NotNull(message = "标签不能为空")
    private String tag;

    /**
     * Tag color.
     */
    @TableField(value= "color")
    private String color;
}
