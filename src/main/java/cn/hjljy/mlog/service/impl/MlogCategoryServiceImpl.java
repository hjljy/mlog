package cn.hjljy.mlog.service.impl;

import cn.hjljy.mlog.common.support.ResultCode;
import cn.hjljy.mlog.common.support.ResultInfo;
import cn.hjljy.mlog.exception.MlogException;
import cn.hjljy.mlog.model.dto.ArticleCategoryDTO;
import cn.hjljy.mlog.model.dto.CategoryDTO;
import cn.hjljy.mlog.model.entity.MlogArticleCategory;
import cn.hjljy.mlog.model.entity.MlogCategory;
import cn.hjljy.mlog.mapper.MlogCategoryMapper;
import cn.hjljy.mlog.service.IMlogArticleCategoryService;
import cn.hjljy.mlog.service.IMlogCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
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
 *  服务实现类
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2021-12-22
 */
@Service
@AllArgsConstructor
public class MlogCategoryServiceImpl extends ServiceImpl<MlogCategoryMapper, MlogCategory> implements IMlogCategoryService {

    private final IMlogArticleCategoryService articleCategoryService;

    @Override
    public List<CategoryDTO> listCategories() {
        return CategoryDTO.convert2DTO(list());
    }

    @Override
    public Boolean create(CategoryDTO dto) {
        Optional<MlogCategory> category = this.getByName(dto.getCategory());
        if(category.isPresent()){
            throw new MlogException(ResultCode.DATA_EXIST,"分类:"+dto.getCategory()+"已存在");
        }
        MlogCategory entity = CategoryDTO.convert2Entity(dto);
        return save(entity);
    }

    @Override
    public Boolean updateCategory(CategoryDTO dto) {
        MlogCategory tags = getById(dto.getId());
        if(null==tags){
            throw new MlogException(ResultCode.DATA_NOT_EXIST,"分类:"+dto.getCategory()+"不存在");
        }
        Optional<MlogCategory> category = getByName(dto.getCategory());
        category.ifPresent(n->{
            if(!dto.getId().equals(n.getId())){
                throw new MlogException(ResultCode.DATA_EXIST,"分类:"+dto.getCategory()+"已存在");
            }
        });
        return updateById(dto);
    }

    @Override
    public Boolean delete(@NotNull(message = "分类id不能为空") Long id) {
       List<MlogArticleCategory> list= articleCategoryService.listByCategoryId(id);
        if (list.size()>0){
            throw new MlogException(ResultCode.NOT_ALLOW,"分类使用当中，无法删除");
        }
        return removeById(id);
    }

    @Override
    public Optional<MlogCategory> getByName(@NotNull(message = "分类名称不能为空") String category) {
        return lambdaQuery().eq(MlogCategory::getCategory,category).oneOpt();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void relateToArticle(@NotNull(message = "文章id不能为空") Long articleId, List<String> categoryList) {
        if (!CollectionUtils.isEmpty(categoryList)) {
            List<Long> categoryIds = new ArrayList<>();
            for (String category : categoryList) {
                MlogCategory mlogCategory = this.saveIfAbsent(category);
                categoryIds.add(mlogCategory.getId());
            }
            articleCategoryService.contact(articleId, categoryIds);
        }
    }

    @Override
    @NonNull
    public MlogCategory saveIfAbsent(@NotNull(message = "分类名称不能为空") String categoryName) {
        Optional<MlogCategory> optional = getByName(categoryName);
        MlogCategory category;
        if (optional.isEmpty()) {
            category = new MlogCategory();
            category.setCategory(categoryName);
            category.setRemark("自动添加");
            save(category);
        } else {
            category = optional.get();
        }
        return category;
    }

    @Override
    public List<ArticleCategoryDTO> getArticleCategories(List<Long> articleIds) {
        if(CollectionUtils.isEmpty(articleIds)){
            return new ArrayList<>();
        }
        return baseMapper.getArticleCategories(articleIds);
    }

    @Override
    public List<ArticleCategoryDTO> getArticleCategoriesById(Long articleId) {
        return baseMapper.getArticleCategoriesById(articleId);
    }
}
