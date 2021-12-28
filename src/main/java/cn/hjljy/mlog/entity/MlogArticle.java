package cn.hjljy.mlog.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;



/**
 * <p>
 * 
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2021-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MlogArticle implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 文章ID
     */
    private Long id;

    /**
     * 文章标题
     */

    private String title;

    /**
     * 文章摘要（md格式）
     */
    private String abstractMd;

    /**
     * 文章摘要（纯文本格式）
     */
    private String abstractText;

    /**
     * 文章内容（md格式）
     */
    private String contentMd;

    /**
     * 文章内容（纯文本格式）
     */
    private String contentText;

    /**
     * 文章标签 (逗号分割)
     */
    private String tags;

    /**
     * 文章作者
     */
    private String authorName;

    /**
     * 文章路径
     */
    private String links;

    /**
     * 封面图链接
     */
    private String thumbnail;

    /**
     *
     * 是否发布 默认不发布
     */
    private Boolean published;

    /**
     * 是否置顶  默认不置顶
     */
    private Boolean top;

    /**
     * 是否允许评论 默认允许
     */
    private Boolean disallowComment;

    /**
     * 评论数
     */
    private Integer commentCount;

    /**
     * 浏览数
     */
    private Integer viewCount;

    /**
     * 文章字数
     */
    private Integer wordCount;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Long createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;


}
