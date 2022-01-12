package cn.hjljy.mlog.mapper;

import cn.hjljy.mlog.model.dto.ArticleTagsDTO;
import cn.hjljy.mlog.model.dto.TagDTO;
import cn.hjljy.mlog.model.entity.MlogTags;
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
public interface MlogTagsMapper extends BaseMapper<MlogTags> {

    /**
     * 列表标签
     *
     * @return {@link List}<{@link TagDTO}>
     */
    List<TagDTO> listTags();

    /**
     * 获取文章标签
     *
     * @param articleIds 文章的id
     * @return {@link List}<{@link ArticleTagsDTO}>
     */
    List<ArticleTagsDTO> getArticleTags(@Param("articleIds") List<Long> articleIds);

    List<ArticleTagsDTO> getArticleTags(Long articleId);
}
