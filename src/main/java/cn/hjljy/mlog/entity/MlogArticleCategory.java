package cn.hjljy.mlog.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class MlogArticleCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 文章id
     */
    private Long articleId;


}
