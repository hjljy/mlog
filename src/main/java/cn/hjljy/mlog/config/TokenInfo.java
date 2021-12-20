package cn.hjljy.mlog.config;

import cn.hjljy.mlog.common.enums.UserType;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 令牌信息
 *
 * @author hjljy
 * @date 2021/09/14
 */
@Data
public class TokenInfo implements Serializable {
    private static final long serialVersionUID = -1L;
    /**
     * token信息
     */
    private String accessToken;
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 账号
     */
    private String username;

    /**
     * 用户类型
     */
    private UserType userType;

    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像
     */
    private String avatar;

    /**
     * 角色
     */
    private String role;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;
}
