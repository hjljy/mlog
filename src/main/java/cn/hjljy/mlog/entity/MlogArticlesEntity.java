package cn.hjljy.mlog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2020-01-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mlog_articles")
public class MlogArticlesEntity implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章摘要内容
     */
    @TableField("AbstractText")
    private String AbstractText;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章标签
     */
    private String tags;

    /**
     * 文章作者
     */
    private String author;

    /**
     * 文章浏览数
     */
    @TableField("viewCount")
    private Integer viewCount;

    /**
     * 文章评论数
     */
    @TableField("commentCount")
    private Integer commentCount;

    /**
     * 文章访问密码
     */
    @TableField("articlePwd")
    private String articlePwd;

    /**
     * 文章访问路径
     */
    @TableField("articleUrl")
    private String articleUrl;

    /**
     * 是否置顶
     */
    private String ontop;

    /**
     * 是否开启评论
     */
    private String commentable;

    /**
     * 是否发布
     */
    private String status;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private Integer createTime;

    /**
     * 更新时间
     */
    @TableField("updateTime")
    private Integer updateTime;


}
