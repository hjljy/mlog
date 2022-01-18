package cn.hjljy.mlog.service.impl;

import cn.hjljy.mlog.common.constants.Constant;
import cn.hjljy.mlog.common.support.ResultCode;
import cn.hjljy.mlog.common.utils.SnowFlakeUtil;
import cn.hjljy.mlog.exception.MlogException;
import cn.hjljy.mlog.mapper.MlogTagsMapper;
import cn.hjljy.mlog.model.dto.ArticleTagsDTO;
import cn.hjljy.mlog.model.dto.TagDTO;
import cn.hjljy.mlog.model.entity.MlogArticleTags;
import cn.hjljy.mlog.model.entity.MlogTags;
import cn.hjljy.mlog.service.IMlogArticleTagsService;
import cn.hjljy.mlog.service.IMlogTagsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2021-12-22
 */
@Service
@AllArgsConstructor
@Slf4j
public class MlogTagsServiceImpl extends ServiceImpl<MlogTagsMapper, MlogTags> implements IMlogTagsService {

    private final IMlogArticleTagsService articleTagsService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void relateToArticle(@NotNull(message = "文章id不能为空") Long articleId, List<String> tags) {
        if (!CollectionUtils.isEmpty(tags)) {
            List<Long> tagIds = new ArrayList<>();
            for (String tagName : tags) {
                MlogTags tag = this.saveIfAbsent(tagName);
                tagIds.add(tag.getId());
            }
            articleTagsService.contact(articleId, tagIds);
        }
    }

    @Override
    @NonNull
    public MlogTags saveIfAbsent(@NotNull(message = "标签名称不能为空") String tagName) {
        Optional<MlogTags> tags = getByName(tagName);
        MlogTags tag;
        if (tags.isEmpty()) {
            tag = new MlogTags();
            tag.setTag(tagName);
            tag.setColor(Constant.DEFAULT_COLOR);
            save(tag);
        } else {
            tag = tags.get();
        }
        return tag;
    }


    @Override
    public Optional<MlogTags> getByName(@NotNull(message = "标签名称不能为空") String tagName) {
        return lambdaQuery().eq(MlogTags::getTag, tagName).oneOpt();
    }


    @Override
    public List<TagDTO> listTags() {
        return baseMapper.listTags();
    }

    @Override
    public Boolean create(TagDTO dto) {
        Optional<MlogTags> tagsOptional = this.getByName(dto.getTag());
        if (tagsOptional.isPresent()) {
            throw new MlogException(ResultCode.DATA_EXIST, "分类:" + dto.getTag() + "已存在");
        }
        MlogTags tags = TagDTO.convert2Entity(dto);
        tags.setId(SnowFlakeUtil.createId());
        return save(tags);
    }

    @Override
    public Boolean updateTag(TagDTO dto) {
        MlogTags tags = getById(dto.getId());
        if (null == tags) {
            throw new MlogException(ResultCode.DATA_NOT_EXIST, "标签:" + dto.getTag() + "不存在");
        }
        Optional<MlogTags> category = getByName(dto.getTag());
        category.ifPresent(n -> {
            if (!dto.getId().equals(n.getId())) {
                throw new MlogException(ResultCode.DATA_EXIST, "标签:" + dto.getTag() + "已存在");
            }
        });
        return updateById(dto);
    }

    @Override
    public Boolean delete(@NotNull(message = "标签id不能为空") Long id) {
        List<MlogArticleTags> list = articleTagsService.getByTagId(id);
        if (list.size() > 0) {
            throw new MlogException(ResultCode.NOT_ALLOW, "标签使用当中，无法删除");
        }
        return removeById(id);
    }

    @Override
    public Integer clean() {
        List<Long> tagIds = articleTagsService.listTagIds();
        QueryWrapper<MlogTags> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().notIn(MlogTags::getId, tagIds);
        return baseMapper.delete(queryWrapper);
    }

    @Override
    public List<ArticleTagsDTO> getArticleTags(List<Long> articleIds) {
        if(CollectionUtils.isEmpty(articleIds)){
            return new ArrayList<>();
        }
        return baseMapper.getArticleTags(articleIds);
    }
}
