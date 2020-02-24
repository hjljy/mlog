package cn.hjljy.mlog.controller;

import cn.hjljy.mlog.common.AjaxResult;
import cn.hjljy.mlog.common.utils.HttpServletRequestUtils;
import cn.hjljy.mlog.entity.MlogArticlesEntity;
import cn.hjljy.mlog.service.IMlogArticlesService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 海加尔金鹰 www.hjljy.cn
 * @version V1.0
 * @email hjljy@outlook.com
 * @description: 后台界面的跳转和相关操作
 * @since 2020/1/22 11:35
 **/
@Controller
@RequestMapping("/mlog")
public class AdminController {

    @Autowired
    IMlogArticlesService mlogArticlesService;

    @GetMapping("/index")
    public String index() {
        return "mlog/index";
    }

    @GetMapping("/article")
    public String article() {
        return "mlog/article";
    }

    @GetMapping("/comment")
    public String comment() {
        return "mlog/comment";
    }

    @GetMapping("/publish")
    public String articlePublish() {
        return "mlog/publish";
    }

    @GetMapping("/allCount")
    @ResponseBody
    public AjaxResult allCount() {
        Map<String,Integer> allCount = new HashMap<>();
        QueryWrapper<MlogArticlesEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(MlogArticlesEntity::getStatus,0);
        int articleCount = mlogArticlesService.count(queryWrapper);
        allCount.put("articleCount",articleCount);
        QueryWrapper<MlogArticlesEntity> lifeQuery = new QueryWrapper<>();
        lifeQuery.lambda().eq(MlogArticlesEntity::getType,1).eq(MlogArticlesEntity::getStatus,0);
        int lifeCount = mlogArticlesService.count(lifeQuery);
        allCount.put("lifeCount",lifeCount);
        QueryWrapper<MlogArticlesEntity> skillQuery = new QueryWrapper<>();
        skillQuery.lambda().eq(MlogArticlesEntity::getType,2).eq(MlogArticlesEntity::getStatus,0);
        int skillCount = mlogArticlesService.count(skillQuery);
        allCount.put("skillCount",skillCount);
        return AjaxResult.SUCCESS(allCount);
    }


}
