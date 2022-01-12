package cn.hjljy.mlog.service;

import cn.hjljy.mlog.model.entity.MlogArticleTags;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2021-12-22
 */
public interface IMlogArticleTagsService extends IService<MlogArticleTags> {

    /**
     * 文章关联标签
     *
     * @param articleId 文章的id
     * @param tagIds    标签id
     */

    void contact(@NotNull(message = "文章id不能为空") Long articleId, List<Long> tagIds);

    /**
     * 通过文章id获取
     *
     * @param articleId 文章的id
     * @return {@link List}<{@link MlogArticleTags}>
     */
    List<MlogArticleTags> getByArticleId(@NotNull(message = "文章id不能为空") Long articleId);

    /**
     * 删除通过文章id
     *
     * @param articleId 文章的id
     */
    void removeByArticleId(@NotNull(message = "文章id不能为空") Long articleId);

    /**
     * 通过标签id获取
     *
     * @param tagId 标签id
     * @return {@link List}<{@link MlogArticleTags}>
     */
    List<MlogArticleTags> getByTagId(@NotNull(message = "标签id不能为空")Long tagId);

    /**
     * 标签id列表
     *
     * @return {@link List}<{@link Long}>
     */
    List<Long> listTagIds();
}
