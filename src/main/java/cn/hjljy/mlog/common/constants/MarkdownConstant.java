package cn.hjljy.mlog.common.constants;

/**
 * markdown相关常量
 * 来源HEXO定义的MD文件常量： https://hexo.io/zh-cn/docs/front-matter.html
 * 来源jekyll定义的MD文件常量： https://jekyllrb.com/docs/front-matter/
 * @author hjljy
 * @date 2021/12/22
 */
public class MarkdownConstant {
    /**
     * markdown 文章标题 标识
     */
    public static final String MD_TITLE = "title";

    /**
     * markdown 文章链接 标识
     */
    public static final String MD_LINK = "permalink";

    /**
     * markdown 文章发布状态 标识  默认：true
     */
    public static final String MD_PUBLISHED = "published";

    /**
     * markdown 文章是否允许评论 标识  默认：true
     */
    public static final String MD_COMMENTS = "comments";

    /**
     * markdown 文章封面图 标识  默认：true
     */
    public static final String MD_THUMBNAIL = "thumbnail";

    /**
     * markdown 文章的标签 标识  由空格分割的字符串或者数组格式 [mlog,java]
     */
    public static final String MD_TAGS = "tags";

    /**
     * markdown 文章的分类 标识  由空格分割的字符串或者数组格式 [mlog,java]
     */
    public static final String MD_CATEGORIES = "categories";

    /**
     * markdown 文章的创建时间 标识   格式:YYYY-MM-DD HH:MM:SS
     */
    public static final String MD_CREATED = "date";

    /**
     * markdown 文章的更新时间 标识   格式:YYYY-MM-DD HH:MM:SS
     */
    public static final String MD_UPDATED = "updated";
}
