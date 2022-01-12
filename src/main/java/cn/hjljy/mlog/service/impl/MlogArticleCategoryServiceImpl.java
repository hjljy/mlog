package cn.hjljy.mlog.service.impl;

import cn.hjljy.mlog.model.entity.MlogArticleCategory;
import cn.hjljy.mlog.mapper.MlogArticleCategoryMapper;
import cn.hjljy.mlog.service.IMlogArticleCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2021-12-22
 */
@Service
public class MlogArticleCategoryServiceImpl extends ServiceImpl<MlogArticleCategoryMapper, MlogArticleCategory> implements IMlogArticleCategoryService {

    @Override
    public List<MlogArticleCategory> listByCategoryId(Long categoryId) {
        return lambdaQuery().eq(MlogArticleCategory::getCategoryId,categoryId).list();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void contact(@NotNull(message = "文章id不能为空")Long articleId, List<Long> categoryIds) {
        this.removeByArticleId(articleId);
        List<MlogArticleCategory> list = new ArrayList<>();
        for (Long categoryId : categoryIds) {
            MlogArticleCategory mac = new MlogArticleCategory();
            mac.setArticleId(articleId);
            mac.setCategoryId(categoryId);
            list.add(mac);
        }
        if (list.size()>0){
            saveBatch(list);
        }
    }

    @Override
    public void removeByArticleId(@NotNull(message = "文章id不能为空") Long articleId) {
        lambdaUpdate().eq(MlogArticleCategory::getArticleId,articleId).remove();
    }
}
