package cn.hjljy.mlog.service;

import cn.hjljy.mlog.entity.MlogArticlesEntity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2020-01-16
 */
public interface IMlogArticlesService extends IService<MlogArticlesEntity> {

    void saveArticle(MlogArticlesEntity articlesEntity);

    Page<MlogArticlesEntity> pageList(int pageSize, int pageNumber, String keywords);

    void updateArticle(MlogArticlesEntity entity);

    void updateCommentCountById(Long articleId);

    List<String> getAllUrl();
}
