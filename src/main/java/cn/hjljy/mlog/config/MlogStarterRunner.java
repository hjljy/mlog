package cn.hjljy.mlog.config;

import cn.hjljy.mlog.cache.CacheStore;
import cn.hjljy.mlog.cache.JsonCacheStore;
import cn.hjljy.mlog.common.constants.SettingConstant;
import cn.hjljy.mlog.common.enums.SettingType;
import cn.hjljy.mlog.entity.MlogSetting;
import cn.hjljy.mlog.service.IMlogSettingService;
import freemarker.template.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * mlog系统启动的时候初始化配置信息
 *
 * @author 海加尔金鹰 www.hjljy.cn
 * @email hjljy@outlook.com
 * @description:
 * @date 2021/10/14
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MlogStarterRunner implements ApplicationRunner {

    @Resource
    JsonCacheStore cacheStore;
    @Resource
    FreemarkerConfiguration freemarker;
    @Resource
    IMlogSettingService settingService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("......初始化系统配置开始......");
        freemarker.loadBlogConfig();
        freemarker.loadAdminInfo();
        log.info("......初始化系统配置完毕......");
    }

    /**
     * 初始化博客设置
     *
     * @param blogSettings 博客设置
     */
    private void initBlogSetting(List<MlogSetting> blogSettings) {

    }

    /**
     * 初始化用户设置
     *
     * @param settings 设置
     */
    private void initUserSetting(List<MlogSetting> settings) {
    }

    /**
     * 初始化主题
     *
     * @param themeName 主题名称
     */
    private void initThemes(String themeName) {

    }
}
