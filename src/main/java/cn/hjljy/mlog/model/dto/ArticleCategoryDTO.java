package cn.hjljy.mlog.model.dto;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hjljy
 * @date 2022/01/05
 */
@Data
public class ArticleCategoryDTO  {

    /**
     * 文章id
     */
    private Long articleId;
    /**
     * 分类
     */
    private String category;

    /**
     * 分类图片
     */
    private String categoryImage;

    /**
     * 路径
     */
    private String fullPath;

    /**
     * 描述
     */
    private String remark;

    /**
     * 获取类别通过文章id
     *
     * @param articleId 文章的id
     * @param dtoList   dto列表
     * @return {@link List}<{@link String}>
     */
    public static List<String> getCategoryByArticleId(Long articleId, List<ArticleCategoryDTO> dtoList){
        return  dtoList.stream().filter(n->n.getArticleId().equals(articleId)).map(ArticleCategoryDTO::getCategory).collect(Collectors.toList());
    }

    /**
     * 获取类别通过文章id
     *
     * @param articleId 文章的id
     * @param dtoList   dto列表
     * @return {@link List}<{@link String}>
     */
    public static List<ArticleCategoryDTO> getCategoriesByArticleId(Long articleId, List<ArticleCategoryDTO> dtoList){
        return  dtoList.stream().filter(n->n.getArticleId().equals(articleId)).map(ArticleCategoryDTO::initFullPath).collect(Collectors.toList());
    }

    private static ArticleCategoryDTO initFullPath(ArticleCategoryDTO articleCategoryDTO) {
        articleCategoryDTO.setFullPath("/categories/"+articleCategoryDTO.category);
        return articleCategoryDTO;
    }
}
