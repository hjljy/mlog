package cn.hjljy.mlog.model.setting;

import lombok.Data;

/**
 * 文章设置
 *
 * @author hjljy
 * @date 2022/01/12
 */
@Data
public class ArticleSetting {
    private Integer pageSize = 10;

    private Integer showPage = 10;

    private String sortField = "update_time";
}
