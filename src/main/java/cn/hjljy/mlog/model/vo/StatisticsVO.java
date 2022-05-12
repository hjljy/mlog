package cn.hjljy.mlog.model.vo;

import lombok.Data;

/**
 * 博客统计数据
 *
 * @author hjljy
 * @date 2022/03/25
 */
@Data
public class StatisticsVO {
    /**
     * 浏览数
     */
    private Integer viewNum;
    /**
     * 文章数
     */
    private Integer articleNum;
    /**
     * 友链数
     */
    private Integer linksNum;
    /**
     * 评论数
     */
    private Integer commentNum;
}
