package cn.hjljy.mlog.service;

import cn.hjljy.mlog.model.enums.SettingOptionKeyEnum;
import cn.hjljy.mlog.model.setting.ArticleSetting;
import cn.hjljy.mlog.model.setting.FileStorageSetting;
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
    FileStorageSetting getFileStorageSetting();

    /**
     * 通过选项关键获取设置
     *
     * @param optionKey 选择键
     * @return {@link MlogSetting}
     */
    MlogSetting getSettingByOptionKey(SettingOptionKeyEnum optionKey);

    /**
     * 获取文章设置
     *
     * @return {@link ArticleSetting}
     */
    ArticleSetting getArticleSetting();

    /**
     * 获取文章每页条数
     *
     * @return {@link Integer}
     */
    Integer getArticlePageSize();

    /**
     * 获取文章排序
     *
     * @return {@link String}
     */
    String getArticleSort();

    /**
     * 获取值通过选项关键
     * 如果不存在，返回默认值
     * @param optionKey 选择键
     * @return {@link String}
     */
    String getValueByOptionKeyOrDefault(SettingOptionKeyEnum optionKey);

    /**
     * 获取主题设置
     *
     * @return {@link String}
     */
    String getThemeSetting();
}
