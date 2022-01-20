package cn.hjljy.mlog.controller.content;

import cn.hjljy.mlog.service.DataModelService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 博客公共页面控制器
 *
 * @author hjljy
 * @date 2022/01/12
 */
@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping
public class PageController {

    private final DataModelService dataModelService;

    /**
     * 博客首页
     *
     * @param model 模型
     * @param p     p
     * @return {@link String}
     */
    @GetMapping()
    public String index(Model model,Integer p){
        if(null==p){
            p=1;
        }
        return dataModelService.indexPage(model,p);
    }

    /**
     * 博客文章展示页面
     *
     * @param model     模型
     * @param articleId 文章的id
     * @return {@link String}
     */
    @GetMapping("/post/{articleId}")
    public String article(Model model, @PathVariable Long articleId){
        return dataModelService.articlePage(model,articleId);
    }
}
