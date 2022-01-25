package cn.hjljy.mlog.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.hjljy.mlog.model.dto.ArticleDTO;
import cn.hjljy.mlog.model.vo.ArticleVO;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;


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

    public static List<ArticleDTO> convert2DTO(List<MlogArticle> list) {
        List<ArticleDTO> data = new ArrayList<>();
        for (MlogArticle article : list) {
            data.add(convert2DTO(article));
        }
        return data;
    }

    public static ArticleVO convert2VO(MlogArticle article) {
        ArticleVO vo = new ArticleVO();
        BeanUtils.copyProperties(article, vo);
        return vo;
    }

    public static List<ArticleVO> convert2VO(List<MlogArticle> dataRecords) {
        List<ArticleVO> list = new ArrayList<>();
        for (MlogArticle dataRecord : dataRecords) {
            list.add(convert2VO(dataRecord));
        }
        return list;
    }

    public static IPage<ArticleDTO> convert2DTO(IPage<MlogArticle> articlePages) {
        IPage<ArticleDTO> page = new Page<>();
        page.setPages(articlePages.getPages());
        page.setSize(articlePages.getSize());
        page.setCurrent(articlePages.getCurrent());
        page.setTotal(articlePages.getTotal());
        page.setRecords(convert2DTO(articlePages.getRecords()));
        return page;
    }
}
