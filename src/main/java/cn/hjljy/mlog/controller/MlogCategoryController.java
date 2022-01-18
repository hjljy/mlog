package cn.hjljy.mlog.controller;


import cn.hjljy.mlog.common.support.ResultInfo;
import cn.hjljy.mlog.model.dto.CategoryDTO;
import cn.hjljy.mlog.service.IMlogCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2021-12-22
 */
@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class MlogCategoryController {

    private final IMlogCategoryService categoryService;

    @GetMapping
    public ResultInfo<List<CategoryDTO>> list(){
        return ResultInfo.success(categoryService.listCategories());
    }

    @PostMapping
    public ResultInfo<Boolean> add(@RequestBody CategoryDTO dto){
        return ResultInfo.success(categoryService.create(dto));
    }

    @PutMapping
    public ResultInfo<Boolean> update(@RequestBody CategoryDTO dto){
        return ResultInfo.success(categoryService.updateCategory(dto));
    }

    @DeleteMapping("/{id}")
    public ResultInfo<Boolean> remove(@PathVariable Long id){
        return ResultInfo.success(categoryService.delete(id));
    }
}

