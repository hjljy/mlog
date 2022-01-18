package cn.hjljy.mlog.model.dto;

import cn.hjljy.mlog.model.entity.MlogArticle;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 文章dto
 *
 * @author hjljy
 * @date 2021/12/20
 */
@Data
@EqualsAndHashCode(callSuper = false)
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
     * 文章标签 数组格式
     */
    private List<String> tagList;

    /**
     * 文章分类 数组格式
     */
    private List<String> categoryList;


    public static MlogArticle convert2Entity(ArticleDTO dto) {
        MlogArticle article = new MlogArticle();
        BeanUtils.copyProperties(dto, article);
        return article;
    }

    public static ArticleDTO convert2DTO(MlogArticle article) {
        ArticleDTO dto = new ArticleDTO();
        BeanUtils.copyProperties(article, dto);
        return dto;
    }

    public static List<ArticleDTO> convert2DTO(List<MlogArticle> article) {
        List<ArticleDTO> result = new ArrayList<>();
        for (MlogArticle mlogArticle : article) {
            result.add(convert2DTO(mlogArticle));
        }
        return result;
    }

    public static String getDefaultLinks(Date date) {
        String format = DateUtil.format(date, "yyyy/MM/dd");
        if (null == format) {
            format = "list";
        }
        return "/articles/" + format + "/" + System.currentTimeMillis() + ".html";
    }
}
