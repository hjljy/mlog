package cn.hjljy.mlog.model.dto;

import cn.hjljy.mlog.model.entity.MlogArticle;
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
public class ArticleDTO extends  MlogArticle implements Serializable {

    private static final long serialVersionUID = 1L;

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
