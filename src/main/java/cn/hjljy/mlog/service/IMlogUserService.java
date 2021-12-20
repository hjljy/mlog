package cn.hjljy.mlog.service;

import cn.hjljy.mlog.entity.MlogUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Optional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2021-09-10
 */
public interface IMlogUserService extends IService<MlogUser> {

    /**
     * 找到通过用户名
     *
     * @param username 用户名
     * @return {@link Optional}<{@link MlogUser}>
     */
    Optional<MlogUser> findByUsername(String username);

    /**
     * 获取管理用户基础信息
     *
     * @return {@link Optional}<{@link MlogUser}>
     */
    Optional<MlogUser> getAdminUserBaseInfo();
}
