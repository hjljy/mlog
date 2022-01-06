package cn.hjljy.mlog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * mlog类别
 * <p>
 *
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @date 2022/01/05
 * @since 2021-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MlogCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 分类
     */
    private String category;

    /**
     * 分类图片
     */
    private String categoryImage;

    /**
     * 描述
     */
    private String remark;


}
