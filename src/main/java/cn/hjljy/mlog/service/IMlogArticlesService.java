package cn.hjljy.mlog.service;

import cn.hjljy.mlog.common.AjaxResult;
import cn.hjljy.mlog.entity.MlogArticlesEntity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
