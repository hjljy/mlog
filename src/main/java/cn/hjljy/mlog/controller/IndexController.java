package cn.hjljy.mlog.controller;

import cn.hjljy.mlog.entity.MlogConfigEntity;
import cn.hjljy.mlog.service.IMlogConfigService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author 海加尔金鹰 www.hjljy.cn
 * @version V1.0
 * @since 2020/1/16 21:21
 * 首页界面
 **/
@Controller
public class IndexController {
    @Autowired
    IMlogConfigService configService;

    @GetMapping
    public String index(Model model){
        //获取博客系统的皮肤信息
        QueryWrapper<MlogConfigEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(MlogConfigEntity::getConfigName,"defaultSkin").eq(MlogConfigEntity::getConfigType,"skin");
        MlogConfigEntity skinConfig = configService.getOne(queryWrapper);

        return "index";
    }
}
