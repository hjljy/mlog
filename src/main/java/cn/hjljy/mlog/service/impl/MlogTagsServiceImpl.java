package cn.hjljy.mlog.service.impl;

import cn.hjljy.mlog.entity.MlogArticleTagsEntity;
import cn.hjljy.mlog.entity.MlogTagsEntity;
import cn.hjljy.mlog.mapper.MlogTagsMapper;
import cn.hjljy.mlog.service.IMlogArticleTagsService;
import cn.hjljy.mlog.service.IMlogTagsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2020-02-08
 */
@Service
public class MlogTagsServiceImpl extends ServiceImpl<MlogTagsMapper, MlogTagsEntity> implements IMlogTagsService {

    @Autowired
    IMlogArticleTagsService mlogArticleTagsService;

    @Override
    public void saveArticleTags(Long articleId, String tags) {
        List<String> tagList = Arrays.asList(tags.split(","));
        for (String tag : tagList) {
            QueryWrapper<MlogTagsEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(MlogTagsEntity::getName, tag);
            MlogTagsEntity tagsEntity = this.getOne(queryWrapper);
            //判断标签是否存在
            if (tagsEntity == null) {
                tagsEntity = new MlogTagsEntity();
                tagsEntity.setName(tag);
                this.save(tagsEntity);
            }
            //关联文章标签信息
            MlogArticleTagsEntity mlogArticleTagsEntity = new MlogArticleTagsEntity();
            mlogArticleTagsEntity.setTagsId(tagsEntity.getId());
            mlogArticleTagsEntity.setArticleId(articleId);
            mlogArticleTagsService.save(mlogArticleTagsEntity);
        }
    }
}
