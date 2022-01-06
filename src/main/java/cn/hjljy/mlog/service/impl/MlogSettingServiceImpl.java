package cn.hjljy.mlog.service.impl;

import cn.hjljy.mlog.common.utils.JacksonUtil;
import cn.hjljy.mlog.model.dto.FileStorageSettingDTO;
import cn.hjljy.mlog.model.enums.SettingOptionKeyEnum;
import cn.hjljy.mlog.model.enums.SettingTypeEnum;
import cn.hjljy.mlog.model.entity.MlogSetting;
import cn.hjljy.mlog.mapper.MlogSettingMapper;
import cn.hjljy.mlog.service.IMlogSettingService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2021-10-15
 */
@Service
public class MlogSettingServiceImpl extends ServiceImpl<MlogSettingMapper, MlogSetting> implements IMlogSettingService {

    @Override
    public List<MlogSetting> getBlogSetting() {
        return getSettingByType(SettingTypeEnum.BLOG);
    }

    @Override
    public List<MlogSetting> getSettingByType(SettingTypeEnum blog) {
        QueryWrapper<MlogSetting> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(MlogSetting::getType, blog);
        return list(wrapper);
    }

    @Override
    public FileStorageSettingDTO getFileStorageSetting() {
        MlogSetting setting = getSettingByOptionKey(SettingOptionKeyEnum.FILE_STORAGE.getOptionKey());
        return JacksonUtil.string2Obj(setting.getOptionValue(),FileStorageSettingDTO.class);
    }

    @Override
    public MlogSetting getSettingByOptionKey(String optionKey) {
        QueryWrapper<MlogSetting> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(MlogSetting::getOptionKey, optionKey);
        return getOne(wrapper);
    }
}
