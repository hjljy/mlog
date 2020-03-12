package cn.hjljy.mlog.service.impl;

import cn.hjljy.mlog.entity.MlogArticleTagsEntity;
import cn.hjljy.mlog.entity.MlogArticlesEntity;
import cn.hjljy.mlog.mapper.MlogArticlesMapper;
import cn.hjljy.mlog.service.IMlogArticleTagsService;
import cn.hjljy.mlog.service.IMlogArticlesService;
import cn.hjljy.mlog.service.IMlogTagsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2020-01-16
 */
@Service
public class MlogArticlesServiceImpl extends ServiceImpl<MlogArticlesMapper, MlogArticlesEntity> implements IMlogArticlesService {

    @Autowired
    IMlogArticleTagsService mlogArticleTagsService;

    @Autowired
    IMlogTagsService mlogTagsService;

    @Override
    @Transactional
    public void saveArticle(MlogArticlesEntity articlesEntity) {
        save(articlesEntity);
        String tags = articlesEntity.getTags();
        mlogTagsService.saveArticleTags(articlesEntity.getId(), tags);
    }

    @Override
    public Page<MlogArticlesEntity> pageList(int pageSize, int pageNumber, String keywords) {
        Page<MlogArticlesEntity> page = new Page<>(pageNumber, pageSize);
        QueryWrapper<MlogArticlesEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(StringUtils.isNotEmpty(keywords),MlogArticlesEntity::getTitle, keywords)
                .or().like(StringUtils.isNotEmpty(keywords),MlogArticlesEntity::getContent, keywords)
                .orderByDesc(MlogArticlesEntity::getCreateTime,MlogArticlesEntity::getOntop);
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    @Transactional
    public void updateArticle(MlogArticlesEntity entity) {
        //解除之前的标签关联关系，重新关联
        QueryWrapper<MlogArticleTagsEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(MlogArticleTagsEntity::getArticleId, entity.getId());
        mlogArticleTagsService.remove(queryWrapper);
        updateById(entity);
        mlogTagsService.saveArticleTags(entity.getId(),entity.getTags());
    }

    @Override
    public void updateCommentCountById(Long articleId) {
        this.baseMapper.updateCommentCountById( articleId);
    }

    @Override
    public List<String> getAllUrl() {
        return this.baseMapper.getAllUrl() ;
    }
}
