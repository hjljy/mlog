package cn.hjljy.mlog.service.impl;

import cn.hjljy.mlog.common.constants.PaginationConstant;
import cn.hjljy.mlog.common.utils.MlogUtils;
import cn.hjljy.mlog.model.dto.ArticleDTO;
import cn.hjljy.mlog.model.params.ArticleQuery;
import cn.hjljy.mlog.model.setting.ArticleSetting;
import cn.hjljy.mlog.model.vo.ArticleVO;
import cn.hjljy.mlog.common.support.PageVO;
import cn.hjljy.mlog.service.DataModelService;
import cn.hjljy.mlog.service.IMlogArticleService;
import cn.hjljy.mlog.service.IMlogSettingService;
import cn.hjljy.mlog.service.ThemeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

/**
 * 数据模型服务impl
 *
 * @author hjljy
 * @date 2022/01/12
 */
@Service
@AllArgsConstructor
public class DataModelServiceImpl implements DataModelService {

    private final IMlogSettingService settingService;

    private final IMlogArticleService articleService;

    private final ThemeService themeService;

    @Override
    public String indexPage(Model model, Integer page) {
        ArticleSetting setting = settingService.getArticleSetting();
        ArticleQuery query = new ArticleQuery();
        query.setPageNumber(page);
        query.setPageSize(setting.getPageSize());
        query.setSortBy(setting.getSortField());
        PageVO<ArticleVO> posts = articleService.pageQuery(query);
        model.addAttribute("is_index", true);
        model.addAttribute("posts", posts);
        //分页信息
        Long totalPages = posts.getTotalPages();
        List<Long> paginate = MlogUtils.paginate(page, totalPages, setting.getShowPage());
        model.addAttribute(PaginationConstant.PAGINATION_PAGE_COUNT, totalPages);
        model.addAttribute(PaginationConstant.PAGINATION_PAGE_NUMS, paginate);
        model.addAttribute(PaginationConstant.PAGINATION_CURRENT_PAGE_NUM, page);
        int previousPageNum = page > 1 ? page - 1 : 0;
        model.addAttribute(PaginationConstant.PAGINATION_PREVIOUS_PAGE_NUM, previousPageNum);
        long nextPageNum = page + 1 > totalPages ? totalPages : page + 1;
        model.addAttribute(PaginationConstant.PAGINATION_NEXT_PAGE_NUM, nextPageNum);
        return themeService.renderIndex();
    }

    @Override
    public String articlePage(Model model, Long articleId) {
        ArticleDTO articleDTO = articleService.getDetailById(articleId);
        ArticleVO vo = articleService.getArticleVO(articleId);
        model.addAttribute("post",vo);
        return themeService.renderArticle();
    }
}
