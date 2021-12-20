package cn.hjljy.mlog.config;

import cn.hjljy.mlog.common.constants.SettingConstant;
import cn.hjljy.mlog.entity.MlogSetting;
import cn.hjljy.mlog.entity.MlogUser;
import cn.hjljy.mlog.service.IMlogSettingService;
import cn.hjljy.mlog.service.IMlogUserService;
import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * freemarker配置
 *
 * @author hjljy
 * @date 2021/10/15
 */
@Component
public class FreemarkerConfiguration {
    private final IMlogUserService userService;
    private final IMlogSettingService settingService;
    private final Configuration configuration;

    public FreemarkerConfiguration(IMlogUserService userService, IMlogSettingService settingService, Configuration configuration) {
        this.userService = userService;
        this.settingService = settingService;
        this.configuration = configuration;
    }

    /**
     * 加载博客配置变量数据
     *
     * @throws TemplateModelException 模板模型异常
     */
    public void loadBlogConfig() throws TemplateModelException {

        List<MlogSetting> blogSetting = settingService.getBlogSetting();
        //将所有变量设置为共享变量
        configuration.setSharedVariable("blogSetting", blogSetting);
        //设置faviconUrl
        String faviconUrl = MlogSetting.getValueByKeyOrDefault(SettingConstant.Blog.FAVICON_URL, blogSetting, SettingConstant.Blog.FAVICON_URL_DEFAULT_VALUE);
        configuration.setSharedVariable(SettingConstant.Blog.FAVICON_URL, faviconUrl);

        String blogTitle = MlogSetting.getValueByKeyOrDefault(SettingConstant.Blog.BLOG_TITLE, blogSetting, SettingConstant.Blog.BLOG_TITLE_DEFAULT_VALUE);
        configuration.setSharedVariable(SettingConstant.Blog.BLOG_TITLE, blogTitle);

    }

    /**
     * 加载网站管理员信息
     */
    public void loadAdminInfo() throws TemplateModelException {
        MlogUser user = userService.getAdminUserBaseInfo().orElse(new MlogUser());
        //将所有变量设置为共享变量
        configuration.setSharedVariable("adminUser", user);
    }
}
