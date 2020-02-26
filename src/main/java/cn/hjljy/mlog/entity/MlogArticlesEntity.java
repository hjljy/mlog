package cn.hjljy.mlog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
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

    @TableId(value = "id")
    private Long id;

    /**
     * 文章标题
     */
    @TableField("title")
    private String title;

    /**
     * 文章摘要内容
     */
    @TableField("abstractText")
    private String abstractText;

    /**
     * 文章内容
     */
    @TableField("content")
    private String content;

    /**
     * 文章标签
     */
    private String tags;
    /**
     * 文章类型
     */
    private Integer type;

    /**
     * 文章作者
     */
    @TableField("authorId")
    private Integer authorId;

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
    private Integer ontop;

    /**
     * 是否开启评论
     */
    private Integer commentable;

    /**
     * 是否发布
     */
    private Integer status;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private Long createTime;

    /**
     * 更新时间
     */
    @TableField("updateTime")
    private Long updateTime;

}
