package cn.hjljy.mlog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2020-02-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mlog_account")
public class MlogAccountEntity implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer id;

    /**
     * 账号类型
     */
    private Integer type;

    /**
     * 账号
     */
    private String account;

    /**
     * 账号所属机构
     */
    private String org;

    /**
     * 账号KEY
     */
    private String accountKey;

    /**
     * 账号密码
     */
    private String pwd;

    /**
     * 账号描述
     */
    private String accountDesc;

    /**
     * 账号其他参数
     */
    private String params;


}
