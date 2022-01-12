package cn.hjljy.mlog.model.dto;

import cn.hjljy.mlog.model.entity.MlogTags;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 标签dto
 *
 * @author hjljy
 * @date 2022/01/04
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TagDTO extends MlogTags {

    private Integer useNum;

    private Long articleId;

    public static MlogTags convert2Entity(TagDTO dto) {
        MlogTags tags = new MlogTags();
        BeanUtils.copyProperties(dto, tags);
        return tags;
    }

    public static TagDTO convert2DTO(MlogTags mlogTags) {
        TagDTO dto = new TagDTO();
        BeanUtils.copyProperties(mlogTags, dto);
        return dto;
    }

    public static List<TagDTO> convert2DTO(List<MlogTags> tagsList) {
        List<TagDTO> list = new ArrayList<>();
        for (MlogTags tags : tagsList) {
            list.add(convert2DTO(tags));
        }
        return list;
    }
}
