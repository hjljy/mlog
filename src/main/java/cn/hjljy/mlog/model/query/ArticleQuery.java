package cn.hjljy.mlog.model.query;

import lombok.Data;

import java.io.Serializable;

/**
 * 文章查询
 *
 * @author hjljy
 * @date 2021/12/17
 */
@Data
public class ArticleQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 文章名称
     */
    private String title;
}
