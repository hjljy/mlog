package cn.hjljy.mlog.model.dto;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 文章标签dto
 *
 * @author hjljy
 * @date 2022/01/12
 */
@Data
public class ArticleTagsDTO{

    /**
     * 文章id
     */
    private Long articleId;

    /**
     * 标签
     */
    private String tag;

    /**
     * 标签  等同于tag
     */
    private String name;

    /**
     * 路径
     */
    private String fullPath;

    /**
     * Tag color.
     */
    private String color;

    public static ArticleTagsDTO init(ArticleTagsDTO dto){
        dto.setFullPath("/tags/"+dto.tag);
        dto.setName(dto.tag);
        return dto;
    }

    /**
     * 获取标签通过文章id
     *
     * @param articleId 文章的id
     * @param dtoList   dto列表
     * @return {@link List}<{@link String}>
     */
    public static List<String> getTagByArticleId(Long articleId,List<ArticleTagsDTO> dtoList){
        return  dtoList.stream().filter(n->n.getArticleId().equals(articleId)).map(ArticleTagsDTO::getTag).collect(Collectors.toList());
    }

    /**
     * 获取标签通过文章id
     *
     * @param articleId 文章的id
     * @param dtoList   dto列表
     * @return {@link List}<{@link String}>
     */
    public static List<ArticleTagsDTO> getTagsByArticleId(Long articleId,List<ArticleTagsDTO> dtoList){
        return  dtoList.stream().filter(n->n.getArticleId().equals(articleId)).map(ArticleTagsDTO::init).collect(Collectors.toList());
    }

}
