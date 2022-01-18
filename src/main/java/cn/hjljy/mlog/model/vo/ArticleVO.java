package cn.hjljy.mlog.model.vo;

import cn.hjljy.mlog.model.dto.ArticleCategoryDTO;
import cn.hjljy.mlog.model.dto.ArticleTagsDTO;
import cn.hjljy.mlog.model.dto.CategoryDTO;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

/**
 * 文章展示数据
 *
 * @author hjljy
 * @date 2022/01/17
 */
@Data
public class ArticleVO {
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

    /**
     * 文章标签
     */
    private List<ArticleTagsDTO> tags;

    /**
     * 文章分类 数组格式
     */
    private List<ArticleCategoryDTO> categories;

}
