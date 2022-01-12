package cn.hjljy.mlog.service.impl;

import cn.hjljy.mlog.model.entity.MlogArticleTags;
import cn.hjljy.mlog.mapper.MlogArticleTagsMapper;
import cn.hjljy.mlog.service.IMlogArticleTagsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
public class MlogArticleTagsServiceImpl extends ServiceImpl<MlogArticleTagsMapper, MlogArticleTags> implements IMlogArticleTagsService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void contact(@NotNull(message = "文章id不能为空") Long articleId, List<Long> tagIds) {
       this.removeByArticleId(articleId);
       List<MlogArticleTags> mlogArticleTags=new ArrayList<>();
        for (Long tagId : tagIds) {
            MlogArticleTags tags = new MlogArticleTags();
            tags.setTagId(tagId);
            tags.setArticleId(articleId);
            mlogArticleTags.add(tags);
        }
        if(!CollectionUtils.isEmpty(mlogArticleTags)){
            this.saveBatch(mlogArticleTags);
        }
    }

    @Override
    public void removeByArticleId(@NotNull(message = "文章id不能为空") Long articleId) {
        lambdaUpdate().eq(MlogArticleTags::getArticleId,articleId).remove();
    }

    @Override
    public List<MlogArticleTags> getByArticleId(@NotNull(message = "文章id不能为空") Long articleId) {
        return lambdaQuery().eq(MlogArticleTags::getArticleId,articleId).list();
    }

    @Override
    public List<MlogArticleTags> getByTagId(@NotNull(message = "标签id不能为空") Long tagId) {
        return lambdaQuery().eq(MlogArticleTags::getTagId,tagId).list();
    }

    @Override
    public List<Long> listTagIds() {
        return baseMapper.listTagIds();
    }
}
