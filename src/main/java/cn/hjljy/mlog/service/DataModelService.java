package cn.hjljy.mlog.service;

import org.springframework.ui.Model;

/**
 * 数据模型服务
 *
 * @author hjljy
 * @date 2022/01/12
 */
public interface DataModelService {
    /**
     * 索引页
     *
     * @param model 模型
     * @param page  当前文章页数
     * @return {@link String}
     */
    String indexPage(Model model, Integer page);

    /**
     * 文章页面
     *
     * @param model     模型
     * @param articleId 文章的id
     * @return {@link String}
     */
    String articlePage(Model model, Long articleId);
}
