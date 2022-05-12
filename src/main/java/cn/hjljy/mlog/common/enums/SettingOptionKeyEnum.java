package cn.hjljy.mlog.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 设置选项键枚举
 *
 * @author hjljy
 * @date 2021/12/31
 */
@Getter
public enum SettingOptionKeyEnum {

    /**
     * 博客标题
     */
    BLOG_TITLE("blogTitle","mlog博客"),

    /**
     * 博客主题
     */
    THEME("theme","anatole"),

    /**
     * 登录背景图像
     */
    LOGIN_BACKGROUND_IMAGE("loginBackgroundImage","https://image.hjljy.cn/image/default.jpg"),

    /**
     * 博客默认favicon url
     */
    FAVICON_URL("faviconUrl","https://image.hjljy.cn/favicon/hjljy.png"),
    /**
     * 文章排序
     */
    ARTICLE_SORT_BY("sortBy","update_time"),
    /**
     * 文章每页数量
     */
    PAGE_SIZE("pageSize","10"),
    /**
     * 文章分页展示数量
     */
    SHOW_PAGE("showPage","10"),
    /**
     * 文件存储
     */
    FILE_STORAGE("fileStorage","{\"type\":\"local\"}");


    @EnumValue
    private final String optionKey;
    private final String defaultValue;
    SettingOptionKeyEnum(String optionKey, String defaultValue) {
        this.optionKey=optionKey;
        this.defaultValue=defaultValue;
    }
}
