package cn.hjljy.mlog.common.constants;

/**
 * url常数
 *
 * @author hjljy
 * @date 2022/03/18
 */
public class UrlConstant {

    /**
     * 特殊请求路径地址
     */
    public static class Special {
        public static final String CATEGORIES="/categories";
    }


    /**
     *  后台管理接口地址
     */
    public static final String ADMIN_API="/admin";

    public static class Admin {
        public static final String ARTICLE_PAGE="/article/page";
    }

    /**
     *  开放api接口地址
     */
    public static final String OPEN_API="/open";
    public static class Open{
        /**
         * 登录背景图像
         */
        public static final String LOGIN_BACKGROUND_IMAGE ="/login/background/image";

        /**
         * 博客基础信息
         */
        public static final String BLOG_BASE_INFO ="/blog/base/info";

        /**
         * 博客的统计数据
         */
        public static final String BLOG_STATISTICS="/blog/statistics";

    }
}
