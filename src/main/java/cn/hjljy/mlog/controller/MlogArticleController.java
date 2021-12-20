package cn.hjljy.mlog.controller;


import cn.hjljy.mlog.common.ResultInfo;
import cn.hjljy.mlog.dto.ArticleDTO;
import cn.hjljy.mlog.query.ArticleQuery;
import cn.hjljy.mlog.service.IMlogArticleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  文章管理
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2021-12-17
 */
@Controller
@RequestMapping("/article")
@AllArgsConstructor
public class MlogArticleController {

    private IMlogArticleService articleService;

    @PostMapping("/page")
    public ResultInfo<IPage<ArticleDTO>> page(@RequestBody ArticleQuery query){
//        articleService.pageByQuery(query.buildPage(ArticleDTO.class),query);
        return null;
    }
}

