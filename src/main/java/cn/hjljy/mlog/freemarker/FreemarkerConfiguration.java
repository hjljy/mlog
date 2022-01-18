package cn.hjljy.mlog.freemarker;

import cn.hjljy.mlog.common.constants.SettingConstant;
import cn.hjljy.mlog.model.entity.MlogSetting;
import cn.hjljy.mlog.model.entity.MlogUser;
import cn.hjljy.mlog.model.enums.SettingOptionKeyEnum;
import cn.hjljy.mlog.service.IMlogSettingService;
import cn.hjljy.mlog.service.IMlogUserService;
import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String faviconUrl = MlogSetting.getValueByKeyOrDefault(SettingOptionKeyEnum.FAVICON_URL, blogSetting);
        configuration.setSharedVariable(SettingConstant.Blog.FAVICON_URL, faviconUrl);

        String blogTitle = MlogSetting.getValueByKeyOrDefault(SettingOptionKeyEnum.BLOG_TITLE, blogSetting);
        configuration.setSharedVariable(SettingConstant.Blog.BLOG_TITLE, blogTitle);

        Map<String,Object> options = new HashMap<>(32);
        options.put("gravatar_source" , "//gravatar.com/avatar/");
        options.put("gravatar_source" , "//gravatar.com/avatar/");
        configuration.setSharedVariable("options",options);
    }

    /**
     * 加载网站管理员信息
     */
    public void loadAdminInfo() throws TemplateModelException {
        MlogUser user = userService.getAdminUserBaseInfo().orElse(new MlogUser());
        //将所有变量设置为共享变量
        configuration.setSharedVariable("user", user);
    }

    public void loadThemeConfig() throws TemplateModelException{
        Map<String,Object> map = new HashMap<>(32);
        map.put("code_pretty","");
        map.put("post_title_uppper",true);
        map.put("scrollbar","#red");
        map.put("rss",true);
        map.put("google_color","#fff");
        map.put("avatar_circle",true);



        configuration.setSharedVariable("theme_base","/themes/anatole/");
        configuration.setSharedVariable("settings",map);

    }
}
