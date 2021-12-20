package cn.hjljy.mlog.service;

import cn.hjljy.mlog.common.enums.SettingType;
import cn.hjljy.mlog.entity.MlogSetting;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2021-10-15
 */
public interface IMlogSettingService extends IService<MlogSetting> {

    /**
     * 获取博客设置
     *
     * @return {@link List}<{@link MlogSetting}>
     */
    List<MlogSetting> getBlogSetting();

    /**
     * 获取设置通过类型
     *
     * @param type 类型
     * @return {@link List}<{@link MlogSetting}>
     */
    List<MlogSetting> getSettingByType(SettingType type);
}
