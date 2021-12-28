package cn.hjljy.mlog.dto;

import cn.hjljy.mlog.entity.MlogArticle;
import cn.hutool.core.date.DateUtil;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章dto
 *
 * @author hjljy
 * @date 2021/12/20
 */
@Data
public class ArticleDTO implements Serializable {

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
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;

    public static MlogArticle convert2Entity(ArticleDTO dto) {
        MlogArticle article =new MlogArticle();
        BeanUtils.copyProperties(dto,article);
        return article;
    }

    public static String getDefaultLinks(Date date){
        String format = DateUtil.format(date, "yyyy/MM/dd");
        if (null == format) {
            format = "list";
        }
        return "/articles/" + format + "/" + System.currentTimeMillis() + ".html";
    }
}
