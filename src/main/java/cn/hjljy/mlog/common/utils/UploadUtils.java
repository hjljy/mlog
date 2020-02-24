package cn.hjljy.mlog.common.utils;

import cn.hjljy.mlog.entity.MlogAccountEntity;
import cn.hjljy.mlog.entity.MlogConfigEntity;
import com.UpYun;
import com.upyun.UpException;

import java.io.File;
import java.io.IOException;

/**
 * @author 海加尔金鹰 www.hjljy.cn
 * @version V1.0
 * @email hjljy@outlook.com
 * @description:
 * @since 2020/2/24 23:24
 **/
public class UploadUtils {

    public static String upyunUploadImage(MlogAccountEntity config, byte[] bytes, String name) throws Exception {
        // 创建又拍云实例
        UpYun upyun = new UpYun(config.getAccountKey(), config.getAccount(), config.getPwd());
        upyun.setApiDomain(UpYun.ED_AUTO);
        upyun.setDebug(true);
        String url = "/image/"+name;
        upyun.writeFile(url,bytes,true);
        return url;
    }
}
