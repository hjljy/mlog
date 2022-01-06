package cn.hjljy.mlog.mapper;

import cn.hjljy.mlog.model.dto.TagDTO;
import cn.hjljy.mlog.model.entity.MlogTags;
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
public interface MlogTagsMapper extends BaseMapper<MlogTags> {

    /**
     * 列表标签
     *
     * @return {@link List}<{@link TagDTO}>
     */
    List<TagDTO> listTags();
}
