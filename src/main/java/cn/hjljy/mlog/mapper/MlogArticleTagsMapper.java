package cn.hjljy.mlog.mapper;

import cn.hjljy.mlog.model.entity.MlogArticleTags;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2021-12-22
 */
public interface MlogArticleTagsMapper extends BaseMapper<MlogArticleTags> {

    /**
     * 标签id列表
     *
     * @return {@link List}<{@link Long}>
     */
    List<Long> listTagIds();
}
