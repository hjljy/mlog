package cn.hjljy.mlog.model.dto;

import cn.hjljy.mlog.model.entity.MlogArticle;
import cn.hutool.core.date.DateUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
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
public class ArticleDTO extends  MlogArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章标签 数组格式
     */
    private List<String> tagList;

    /**
     * 文章分类 数组格式
     */
    private List<String> categoryList;
    public static MlogArticle convert2Entity(ArticleDTO dto) {
        MlogArticle article =new MlogArticle();
        BeanUtils.copyProperties(dto,article);
        return article;
    }

    public static ArticleDTO convert2DTO(MlogArticle article) {
        ArticleDTO dto =new ArticleDTO();
        BeanUtils.copyProperties(article,dto);
        return dto;
    }


    public static String getDefaultLinks(Date date){
        String format = DateUtil.format(date, "yyyy/MM/dd");
        if (null == format) {
            format = "list";
        }
        return "/articles/" + format + "/" + System.currentTimeMillis() + ".html";
    }
}
