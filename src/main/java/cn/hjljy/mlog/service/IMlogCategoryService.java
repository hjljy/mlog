package cn.hjljy.mlog.service;

import cn.hjljy.mlog.model.dto.ArticleCategoryDTO;
import cn.hjljy.mlog.model.dto.CategoryDTO;
import cn.hjljy.mlog.model.entity.MlogCategory;
import cn.hjljy.mlog.model.entity.MlogTags;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2021-12-22
 */
public interface IMlogCategoryService extends IService<MlogCategory> {

    /**
     * 类别列表
     *
     * @return {@link List}<{@link CategoryDTO}>
     */
    List<CategoryDTO> listCategories();

    /**
     * 创建
     *
     * @param dto dto
     * @return {@link Boolean}
     */
    Boolean create(CategoryDTO dto);

    /**
     * 更新类别
     *
     * @param dto dto
     * @return {@link Boolean}
     */
    Boolean updateCategory(CategoryDTO dto);

    /**
     * 删除
     *
     * @param id id
     * @return {@link Boolean}
     */
    Boolean delete(@NotNull(message = "分类id不能为空") Long id);

    /**
     * 通过名字获取
     *
     * @param category 类别
     * @return {@link MlogCategory}
     */
    Optional<MlogCategory> getByName(@NotNull(message = "分类名称不能为空") String category);

    /**
     * 分类关联文章
     *
     * @param articleId    文章id
     * @param categoryList 类别列表
     */
    void relateToArticle(@NotNull(message = "文章id不能为空") Long articleId, List<String> categoryList);

    /**
     * 如果分类不存在就新增保存  否者返回分类信息
     *
     * @param categoryName 分类名
     * @return {@link MlogCategory}
     */
    @NonNull
    MlogCategory saveIfAbsent(@NotNull(message = "分类名称不能为空") String categoryName);

    /**
     * 获取文章类别
     *
     * @param articleIds 文章的id
     * @return {@link List}<{@link ArticleCategoryDTO}>
     */
    List<ArticleCategoryDTO> getArticleCategories(List<Long> articleIds);

    /**
     * 获取文章类别通过id
     *
     * @param articleId 文章的id
     * @return {@link List}<{@link ArticleCategoryDTO}>
     */
    List<ArticleCategoryDTO> getArticleCategoriesById(Long articleId);
}
