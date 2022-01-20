package cn.hjljy.mlog.mapper;

import cn.hjljy.mlog.model.dto.ArticleCategoryDTO;
import cn.hjljy.mlog.model.entity.MlogCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2021-12-22
 */
public interface MlogCategoryMapper extends BaseMapper<MlogCategory> {

    /**
     * 获取文章类别
     *
     * @param articleIds 文章的id
     * @return {@link List}<{@link ArticleCategoryDTO}>
     */
    List<ArticleCategoryDTO> getArticleCategories(@Param("articleIds") List<Long> articleIds);

    /**
     * 获取文章类别通过id
     *
     * @param articleId 文章的id
     * @return {@link List}<{@link ArticleCategoryDTO}>
     */
    List<ArticleCategoryDTO> getArticleCategoriesById(Long articleId);
}
