package cn.hjljy.mlog.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录用户
 *
 * @author hjljy
 * @date 2021/10/08
 */
@Data
public class LoginUserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
