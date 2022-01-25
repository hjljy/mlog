package cn.hjljy.mlog.model.params;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 文章参数
 *
 * @author hjljy
 * @date 2022/01/21
 */
@Data
public class ArticleParams {

    /**
     * 文章id
     */
    @NotNull(message = "文章id不能为空")
    private Long articleId;

    /**
     * 是否置顶  默认不置顶
     */
    private Boolean top;

    /**
     * 是否允许评论 默认允许
     */
    private Boolean disallowComment;
}
