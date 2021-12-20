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
 * @since 2021-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MlogArticle implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 文章ID
     */
    private Integer id;

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
     * 文章状态 （1 草稿  2 已发布 3 删除）
     */
    private Boolean status;

    /**
     * 是否置顶
     */
    private Boolean top;

    /**
     * 评论数
     */
    private Integer commentCount;

    /**
     * 浏览数
     */
    private Integer viewCount;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;


}
