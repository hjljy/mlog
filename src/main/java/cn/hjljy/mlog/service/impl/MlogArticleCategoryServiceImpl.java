package cn.hjljy.mlog.service.impl;

import cn.hjljy.mlog.model.entity.MlogArticleCategory;
import cn.hjljy.mlog.mapper.MlogArticleCategoryMapper;
import cn.hjljy.mlog.service.IMlogArticleCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
    public List<MlogArticleCategory> listByCategoryId(Long id) {
        return lambdaQuery().eq(MlogArticleCategory::getCategoryId,id).list();
    }
}
