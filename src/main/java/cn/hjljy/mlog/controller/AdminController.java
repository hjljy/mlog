package cn.hjljy.mlog.controller;

import cn.hjljy.mlog.common.AjaxResult;
import cn.hjljy.mlog.common.constants.Constant;
import cn.hjljy.mlog.common.utils.HttpServletRequestUtils;
import cn.hjljy.mlog.entity.MlogArticlesEntity;
import cn.hjljy.mlog.entity.MlogConfigEntity;
import cn.hjljy.mlog.service.IMlogArticlesService;
import cn.hjljy.mlog.service.IMlogCommentService;
import cn.hjljy.mlog.service.IMlogConfigService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class AdminController extends BaseController {

    @Autowired
    IMlogArticlesService mlogArticlesService;

    @Autowired
    IMlogCommentService commentService;
    @Autowired
    IMlogConfigService configService;

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
        Map<String, Number> allCount = new HashMap<>();
        //获取所有已发布的文章数量
        QueryWrapper<MlogArticlesEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(MlogArticlesEntity::getStatus, 0);
        List<MlogArticlesEntity> list = mlogArticlesService.list(queryWrapper);
        if(list==null){
            list=new ArrayList<>();
        }
        int articleCount = list.size();
        allCount.put("articleCount", articleCount);
        //获取生活类文章数量
        long lifeCount = list.stream().filter(article -> article.getType() == 1).count();
        allCount.put("lifeCount", lifeCount);
        //获取技术类文章数量
        long skillCount = list.stream().filter(article -> article.getType() == 2).count();
        allCount.put("skillCount", skillCount);
        //获取所有的评论数量
        int commentCount = commentService.count();
        allCount.put("commentCount", commentCount);
        return AjaxResult.SUCCESS(allCount);
    }


}
