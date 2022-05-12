package cn.hjljy.mlog.model.entity;

import cn.hjljy.mlog.common.enums.UserTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户信息实体
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2021-10-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MlogUser implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户类型
     */
    private UserTypeEnum userType;

    /**
     * 邮箱
     */
    private String email;
    /**
     * 用户网站
     */
    private String userUrl;
    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 笔名
     */
    private String penName;

    /**
     * 角色
     */
    private String role;

    /**
     * 是否有后台权限
     *
     * @return Boolean
     */
    public Boolean hasBackPermission() {
        return UserTypeEnum.ADMIN.equals(userType);
    }


}
