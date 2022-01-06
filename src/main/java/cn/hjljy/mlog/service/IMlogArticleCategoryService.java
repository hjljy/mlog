package cn.hjljy.mlog.service;

import cn.hjljy.mlog.model.entity.MlogArticleCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2021-12-22
 */
public interface IMlogArticleCategoryService extends IService<MlogArticleCategory> {

    /**
     * 列表通过类别id
     *
     * @param id id
     * @return {@link List}<{@link MlogArticleCategory}>
     */
    List<MlogArticleCategory> listByCategoryId(Long id);
}
