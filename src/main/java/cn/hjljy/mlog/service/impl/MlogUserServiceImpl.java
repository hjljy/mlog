package cn.hjljy.mlog.service.impl;

import cn.hjljy.mlog.common.enums.UserType;
import cn.hjljy.mlog.entity.MlogUser;
import cn.hjljy.mlog.mapper.MlogUserMapper;
import cn.hjljy.mlog.service.IMlogUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 海加尔金鹰（www.hjljy.cn）
 * @since 2021-09-10
 */
@Service
public class MlogUserServiceImpl extends ServiceImpl<MlogUserMapper, MlogUser> implements IMlogUserService {

    @Override
    public Optional<MlogUser> findByUsername(String username) {
        return Optional.ofNullable(lambdaQuery().eq(MlogUser::getUsername, username).one());
    }

    @Override
    public Optional<MlogUser> getAdminUserBaseInfo() {
        return Optional.ofNullable(lambdaQuery().select(MlogUser::getUsername,MlogUser::getUserAvatar,MlogUser::getEmail,MlogUser::getUserUrl).eq(MlogUser::getUserType, UserType.ADMIN).one());
    }
}
