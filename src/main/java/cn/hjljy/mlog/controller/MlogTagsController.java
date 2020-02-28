package cn.hjljy.mlog.controller;


import cn.hjljy.mlog.common.AjaxResult;
import cn.hjljy.mlog.entity.MlogTagsEntity;
import cn.hjljy.mlog.service.IMlogTagsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2020-02-08
 */
@RestController
@RequestMapping("/mlog/tags")
public class MlogTagsController extends BaseController{
    @Autowired
    IMlogTagsService mlogTagsService;

    @GetMapping("/all")
    public AjaxResult getAllTags(){
        QueryWrapper<MlogTagsEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name");
        List<MlogTagsEntity> list = mlogTagsService.list(queryWrapper);
        Set<String> collect = list.stream().map(MlogTagsEntity::getName).collect(Collectors.toSet());
        return AjaxResult.SUCCESS(collect);
    }
}

