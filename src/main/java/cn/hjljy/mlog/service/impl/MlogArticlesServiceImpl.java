package cn.hjljy.mlog.service.impl;

import cn.hjljy.mlog.common.AjaxResult;
import cn.hjljy.mlog.entity.MlogArticleTagsEntity;
import cn.hjljy.mlog.entity.MlogArticlesEntity;
import cn.hjljy.mlog.entity.MlogTagsEntity;
import cn.hjljy.mlog.mapper.MlogArticlesMapper;
import cn.hjljy.mlog.service.IMlogArticleTagsService;
import cn.hjljy.mlog.service.IMlogArticlesService;
import cn.hjljy.mlog.service.IMlogTagsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  服务实现类
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
        List<String> tagList = Arrays.asList(tags.split(","));
        for (String tag : tagList) {
            QueryWrapper<MlogTagsEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(MlogTagsEntity::getName,tag);
            MlogTagsEntity tagsEntity = mlogTagsService.getOne(queryWrapper);
            if(tagsEntity==null){
                tagsEntity =new MlogTagsEntity();
                tagsEntity.setName(tag);
                mlogTagsService.save(tagsEntity);
            }
            MlogArticleTagsEntity mlogArticleTagsEntity = new MlogArticleTagsEntity();
            mlogArticleTagsEntity.setTagsId(tagsEntity.getId());
            mlogArticleTagsEntity.setArticleId(articlesEntity.getId());
            mlogArticleTagsService.save(mlogArticleTagsEntity);
        }
    }

    @Override
    public Page<MlogArticlesEntity> pageList(int pageSize, int pageNumber, String keywords) {
        Page<MlogArticlesEntity> page = new Page<>(pageNumber,pageSize);
        QueryWrapper<MlogArticlesEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(MlogArticlesEntity::getTitle,keywords)
                .or().like(MlogArticlesEntity::getContent,keywords)
                .orderByDesc(MlogArticlesEntity::getCreateTime);
        return baseMapper.selectPage(page,queryWrapper);
    }
}
