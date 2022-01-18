package cn.hjljy.mlog.service.impl;

import cn.hjljy.mlog.service.IMlogSettingService;
import cn.hjljy.mlog.service.ThemeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 主题服务具体实现
 *
 * @author hjljy
 * @date 2022/01/17
 */
@Slf4j
@Service
@AllArgsConstructor
public class ThemeServiceImpl implements ThemeService {

    private final IMlogSettingService settingService;
    @Override
    public String renderIndex() {
        String themeName = settingService.getThemeSetting();
        return this.render(themeName,"index");
    }

    @Override
    public String render(String themeName, String pageName) {
        return "themes/"+themeName+"/"+pageName;
    }
}
