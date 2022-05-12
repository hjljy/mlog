package cn.hjljy.mlog.model.dto;

import cn.hjljy.mlog.model.entity.MlogCategory;
import cn.hjljy.mlog.model.entity.MlogTags;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hjljy
 * @date 2022/01/05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CategoryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 分类
     */
    private String category;

    /**
     * 分类图片
     */
    private String categoryImage;

    /**
     * 描述
     */
    private String remark;

    public static MlogCategory convert2Entity(CategoryDTO dto) {
        MlogCategory tags = new MlogCategory();
        BeanUtils.copyProperties(dto, tags);
        return tags;
    }

    public static CategoryDTO convert2DTO(MlogCategory mlogTags) {
        CategoryDTO dto = new CategoryDTO();
        BeanUtils.copyProperties(mlogTags, dto);
        return dto;
    }

    public static List<CategoryDTO> convert2DTO(List<MlogCategory> tagsList) {
        List<CategoryDTO> list = new ArrayList<>();
        for (MlogCategory tags : tagsList) {
            list.add(convert2DTO(tags));
        }
        return list;
    }
}
