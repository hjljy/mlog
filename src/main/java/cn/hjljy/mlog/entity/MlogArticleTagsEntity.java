package cn.hjljy.mlog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2020-02-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mlog_article_tags")
public class MlogArticleTagsEntity implements Serializable {

    private static final long serialVersionUID=1L;

    private Long id;

    /**
     * 标签ID
     */
    @TableField("tagsId")
    private Long tagsId;

    /**
     * 文章ID
     */
    @TableField("articleId")
    private Long articleId;


}
