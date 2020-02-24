package cn.hjljy.mlog.common.constants;

import cn.hjljy.mlog.entity.MlogAccountEntity;
import cn.hjljy.mlog.entity.MlogConfigEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 海加尔金鹰 www.hjljy.cn
 * @version V1.0
 * @email hjljy@outlook.com
 * @description: 常量数据
 * @since 2020/1/11 1:35
 **/
public class Constant {

    public static final int FAIL_CODE=1000;
    public static final int SUCCESS_CODE=0;
    public static final int DEFAULT_CODE=-1;

    /**
     * 数据获取失败
     */
    public static final String OP_FAIL = "操作失败";

    /**
     * 数据获取失败
     */
    public static final String OP_SUCCEED = "操作成功";

    /**
     * 系统配置信息
     */
    public static List<MlogConfigEntity> MLOG_CONFIG =  new ArrayList<>();

    /**
     * 系统配置账号信息
     */
    public static List<MlogAccountEntity> MLOG_ACCOUNT =  new ArrayList<>();
}
