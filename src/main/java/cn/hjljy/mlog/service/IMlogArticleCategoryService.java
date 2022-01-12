package cn.hjljy.mlog.service;

import cn.hjljy.mlog.model.entity.MlogArticleCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2021-12-22
 */
public interface IMlogArticleCategoryService extends IService<MlogArticleCategory> {

    /**
     * 列表通过类别id
     *
     * @param categoryId id
     * @return {@link List}<{@link MlogArticleCategory}>
     */
    List<MlogArticleCategory> listByCategoryId(Long categoryId);

    /**
     * 文章关联分类
     *
     * @param articleId   文章的id
     * @param categoryIds 类别id
     */
    void contact(@NotNull(message = "文章id不能为空") Long articleId, List<Long> categoryIds);

    /**
     * 删除通过文章id
     *
     * @param articleId 文章的id
     */
    void removeByArticleId(@NotNull(message = "文章id不能为空")Long articleId);
}
