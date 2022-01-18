package cn.hjljy.mlog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2022-01-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("mlog_page_navigation")
public class MlogPageNavigation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 页面路径
     */
    @TableField("url")
    private String url;

    /**
     * 优先权
     */
    @TableField("priority")
    private Integer priority;

    /**
     * 页面名称
     */
    @TableField("name")
    private String name;

    @TableField("icon")
    private String icon;

    /**
     * 打开方式
     */
    @TableField("target")
    private String target;


}
