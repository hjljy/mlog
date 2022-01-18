package cn.hjljy.mlog.service;

public interface ThemeService {
    /**
     * 呈现指数
     *
     * @return {@link String}
     */
    String renderIndex();

    /**
     * 渲染
     *
     * @param themeName 主题名称
     * @param pageName  页面名称
     * @return {@link String}
     */
    String render(String themeName, String pageName);
}
