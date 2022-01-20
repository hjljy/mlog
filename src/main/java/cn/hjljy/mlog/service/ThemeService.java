package cn.hjljy.mlog.service;

public interface ThemeService {
    /**
     * 渲染首页
     *
     * @return {@link String}
     */
    String renderIndex();

    /**
     * 渲染文章
     *
     * @return {@link String}
     */
    String renderArticle();

    /**
     * 渲染
     *
     * @param themeName 主题名称
     * @param pageName  页面名称
     * @return {@link String}
     */
    String render(String themeName, String pageName);


}
