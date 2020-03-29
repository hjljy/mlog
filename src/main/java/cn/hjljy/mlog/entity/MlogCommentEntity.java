package cn.hjljy.mlog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2020-03-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mlog_comment")
public class MlogCommentEntity implements Serializable {

    private static final long serialVersionUID=1L;

    private Long id;

    private Long articleId;

    private Long userId;

    private String userName;

    private String userWebsite;

    private String userHeadUrl;

    private String userEmail;

    private String content;

    private Long createTime;

    /**
     * 评论类型：1 评论  2 回复
     */
    private Integer type;

    /**
     * 回复的评论ID
     */
    private Long repId;

    /**
     * 状态
     */
    private int status;
    /**
     * 是否阅读
     */
    private int readStatus;


}
