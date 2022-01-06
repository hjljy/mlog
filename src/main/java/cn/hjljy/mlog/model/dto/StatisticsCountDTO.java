package cn.hjljy.mlog.model.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 统计计数dto
 *
 * @author hjljy
 * @date 2021/12/16
 */
@Data
@Builder
public class StatisticsCountDTO {
    /**
     * 浏览数
     */
    private Integer view;
    /**
     * 文章数
     */
    private Integer article;
    /**
     * 友链数
     */
    private Integer links;
    /**
     * 评论数
     */
    private Integer comment;
}
