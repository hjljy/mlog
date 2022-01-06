package cn.hjljy.mlog.service;

import cn.hjljy.mlog.model.dto.FileStorageSettingDTO;
import cn.hjljy.mlog.model.enums.SettingTypeEnum;
import cn.hjljy.mlog.model.entity.MlogSetting;
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
     * 通过类型获取设置
     *
     * @param type 类型
     * @return {@link List}<{@link MlogSetting}>
     */
    List<MlogSetting> getSettingByType(SettingTypeEnum type);

    /**
     * 获取文件存储设置
     *
     * @return {@link MlogSetting}
     */
    FileStorageSettingDTO getFileStorageSetting();

    /**
     * 通过选项关键获取设置
     *
     * @param optionKey 选择键
     * @return {@link MlogSetting}
     */
    MlogSetting getSettingByOptionKey(String optionKey);
}
