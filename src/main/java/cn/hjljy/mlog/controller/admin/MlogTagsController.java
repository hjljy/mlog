package cn.hjljy.mlog.controller.admin;


import cn.hjljy.mlog.common.support.ResultInfo;
import cn.hjljy.mlog.common.valid.Insert;
import cn.hjljy.mlog.common.valid.Update;
import cn.hjljy.mlog.model.dto.TagDTO;
import cn.hjljy.mlog.service.IMlogTagsService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  标签 前端控制器
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2021-12-22
 */
@RestController
@RequestMapping("/admin/tags")
@AllArgsConstructor
public class MlogTagsController {

    private  final IMlogTagsService tagsService;

    /**
     * 标签列表查询
     *
     */
    @GetMapping()
    public ResultInfo<List<TagDTO>> list(){
        return ResultInfo.success(tagsService.listTags());
    }

    /**
     * 新增标签
     */
    @PostMapping()
    public ResultInfo<Boolean> add(@Validated({Insert.class}) @RequestBody TagDTO dto){
        return ResultInfo.success(tagsService.create(dto));
    }

    /**
     * 修改标签
     */
    @PutMapping()
    public ResultInfo<Boolean> update(@Validated({Update.class})  @RequestBody TagDTO dto){
        return ResultInfo.success(tagsService.updateTag(dto));
    }

    /**
     * 删除标签
     */
    @DeleteMapping("/{id}")
    public ResultInfo<Boolean> remove(@PathVariable Long id){
        return ResultInfo.success(tagsService.delete(id));
    }

    /**
     * 清理未使用的标签
     */
    @DeleteMapping("/clean")
    public ResultInfo<Integer> clean(){
        return ResultInfo.success(tagsService.clean());
    }
}

