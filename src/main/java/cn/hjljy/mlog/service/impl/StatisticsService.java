package cn.hjljy.mlog.service.impl;

import cn.hjljy.mlog.common.enums.ArticleStatusEnum;
import cn.hjljy.mlog.model.vo.StatisticsVO;
import cn.hjljy.mlog.service.IMlogArticleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 统计服务
 *
 * @author hjljy
 * @date 2022/03/25
 */
@Service
@AllArgsConstructor
public class StatisticsService {
    private final IMlogArticleService articleService;

    public StatisticsVO getBlogStatistics() {
        StatisticsVO vo=new StatisticsVO();
        vo.setArticleNum(articleService.countArticle(true));
        vo.setViewNum(articleService.countView(true));
        //TODO 评论数统计和友链数统计
        return vo;
    }
}
