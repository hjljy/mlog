package cn.hjljy.mlog.service.impl;

import cn.hjljy.mlog.common.enums.SettingType;
import cn.hjljy.mlog.entity.MlogSetting;
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
        return getSettingByType(SettingType.BLOG);
    }

    @Override
    public List<MlogSetting> getSettingByType(SettingType blog) {
        QueryWrapper<MlogSetting> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(MlogSetting::getType, blog);
        return list(wrapper);
    }
}
