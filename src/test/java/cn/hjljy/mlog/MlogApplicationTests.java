package cn.hjljy.mlog;

import cn.hjljy.mlog.entity.MlogUserEntity;
import cn.hjljy.mlog.mapper.MlogUserMapper;
import cn.hjljy.mlog.service.IMlogUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MlogApplicationTests {

    @Autowired
    IMlogUserService userService;

    @Test
    void contextLoads() {
        MlogUserEntity mlogUserEntity =new MlogUserEntity();
        mlogUserEntity.setUsername("admin");
        mlogUserEntity.setRoleName("admin");
        mlogUserEntity.setPassword("password");
        QueryWrapper<MlogUserEntity> queryWrapper =new QueryWrapper<>();
        queryWrapper.lambda().eq(MlogUserEntity::getPassword,mlogUserEntity.getPassword())
                .eq(MlogUserEntity::getRoleName,mlogUserEntity.getRoleName());


        userService.list(queryWrapper);
    }

}
