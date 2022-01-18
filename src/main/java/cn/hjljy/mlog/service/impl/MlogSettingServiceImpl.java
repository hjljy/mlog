package cn.hjljy.mlog.service.impl;

import cn.hjljy.mlog.cache.JsonCacheStore;
import cn.hjljy.mlog.common.utils.JacksonUtil;
import cn.hjljy.mlog.model.setting.ArticleSetting;
import cn.hjljy.mlog.model.setting.FileStorageSetting;
import cn.hjljy.mlog.model.enums.SettingOptionKeyEnum;
import cn.hjljy.mlog.model.enums.SettingTypeEnum;
import cn.hjljy.mlog.model.entity.MlogSetting;
import cn.hjljy.mlog.mapper.MlogSettingMapper;
import cn.hjljy.mlog.service.IMlogSettingService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2021-10-15
 */
@Slf4j
@Service
@AllArgsConstructor
public class MlogSettingServiceImpl extends ServiceImpl<MlogSettingMapper, MlogSetting> implements IMlogSettingService {

    private final JsonCacheStore cacheStore;
    @Override
    public List<MlogSetting> getBlogSetting() {
        return getSettingByType(SettingTypeEnum.BLOG);
    }

    @Override
    public List<MlogSetting> getSettingByType(SettingTypeEnum settingType) {
        QueryWrapper<MlogSetting> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(MlogSetting::getType, settingType);
        return list(wrapper);
    }

    @Override
    public FileStorageSetting getFileStorageSetting() {
        MlogSetting setting = getSettingByOptionKey(SettingOptionKeyEnum.FILE_STORAGE);
        return JacksonUtil.string2Obj(setting.getOptionValue(), FileStorageSetting.class);
    }

    @Override
    public MlogSetting getSettingByOptionKey(SettingOptionKeyEnum optionKey) {
        QueryWrapper<MlogSetting> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(MlogSetting::getOptionKey, optionKey);
        return getOne(wrapper);
    }

    @Override
    public Integer getArticlePageSize() {
        try {
            String optionValue =  getValueByOptionKeyOrDefault(SettingOptionKeyEnum.PAGE_SIZE);
            return Integer.valueOf(optionValue);
        }catch (NumberFormatException ex){
            log.warn("文章分页设置错误，请检查并设置成数字类型");
        }
        return Integer.valueOf(SettingOptionKeyEnum.PAGE_SIZE.getDefaultValue());
    }

    @Override
    public String getArticleSort() {
        return getValueByOptionKeyOrDefault(SettingOptionKeyEnum.ARTICLE_SORT_BY);
    }

    @Override
    public String getThemeSetting() {
        return getValueByOptionKeyOrDefault(SettingOptionKeyEnum.THEME);
    }

    @Override
    public String getValueByOptionKeyOrDefault(SettingOptionKeyEnum optionKey) {
        MlogSetting setting = getSettingByOptionKey(optionKey);
        if(null==setting){
            return optionKey.getDefaultValue();
        }
        return setting.getValueOrDefault();
    }

    @Override
    public ArticleSetting getArticleSetting() {
        ArticleSetting as = new ArticleSetting();
        List<MlogSetting> settings = getSettingByType(SettingTypeEnum.ARTICLE);
        int pageSize = 10;
        int showPage = 10;
        try {
            String value = MlogSetting.getValueByKeyOrDefault(SettingOptionKeyEnum.PAGE_SIZE, settings);
            pageSize = Integer.parseInt(value);
        }catch (NumberFormatException ex){
            log.warn("文章分页每页数量设置错误，请检查并设置成数字类型");
        }
        try {
            String value = MlogSetting.getValueByKeyOrDefault(SettingOptionKeyEnum.SHOW_PAGE, settings);
            showPage = Integer.parseInt(value);
        }catch (NumberFormatException ex){
            log.warn("文章分页展示页设置错误，请检查并设置成数字类型");
        }
        String sortBy = MlogSetting.getValueByKeyOrDefault(SettingOptionKeyEnum.ARTICLE_SORT_BY, settings);

        as.setSortField(sortBy);
        as.setPageSize(pageSize);
        as.setShowPage(showPage);
        return as;
    }
}
