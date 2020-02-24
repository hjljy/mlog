package cn.hjljy.mlog.config;

import cn.hjljy.mlog.common.constants.Constant;
import cn.hjljy.mlog.service.IMlogAccountService;
import cn.hjljy.mlog.service.IMlogConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author 海加尔金鹰 www.hjljy.cn
 * @version V1.0
 * @email hjljy@outlook.com
 * @description: 系统启动的时候初始化配置信息
 * @since 2020/2/24 23:01
 **/
@Component
public class MlogConfigInit {

    @Autowired
    IMlogConfigService configService;

    @Autowired
    IMlogAccountService accountService;

    @PostConstruct
    public void initConfig() {
        Constant.MLOG_CONFIG = configService.list();
        Constant.MLOG_ACCOUNT = accountService.list();
    }
}
