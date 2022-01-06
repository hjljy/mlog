package cn.hjljy.mlog.service;

import cn.hjljy.mlog.model.dto.TagDTO;
import cn.hjljy.mlog.model.entity.MlogTags;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

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
public interface IMlogTagsService extends IService<MlogTags> {

    /**
     * 标签关联文章
     *
     * @param articleId 文章的id
     * @param tags      标签
     */

    void relateToArticle(@NotNull(message = "文章id不能为空") Long articleId, String tags);

    /**
     * 通过名字获取标签
     *
     * @param tagName 标签名
     * @return {@link MlogTags}
     */
    Optional<MlogTags> getByName(@NotNull(message = "标签名称不能为空") String tagName);

    /**
     * 如果标签不存在就新增保存  否者返回标签信息
     *
     * @param tagName 标签名
     * @return {@link MlogTags}
     */
    @NonNull
    MlogTags saveIfAbsent(@NotNull(message = "标签名称不能为空") String tagName);

    /**
     * 列表标签
     *
     * @return {@link List}<{@link TagDTO}>
     */
    List<TagDTO> listTags();

    /**
     * 创建
     *
     * @param dto dto
     * @return {@link Boolean}
     */
    Boolean create(TagDTO dto);

    /**
     * 更新标签
     *
     * @param dto dto
     * @return {@link Boolean}
     */
    Boolean updateTag(TagDTO dto);

    /**
     * 删除
     *
     * @param id id
     * @return {@link Boolean}
     */
    Boolean delete(@NotNull(message = "标签id不能为空") Long id);

    /**
     * 清理未使用的标签
     *
     * @return {@link Integer}
     */
    Integer clean();
}
