package cn.hjljy.mlog.common.annotation;

import java.lang.annotation.*;

/**
 * @author 海加尔金鹰 www.hjljy.cn
 * @version V1.0
 * @email hjljy@outlook.com
 * @description: 操作日志注解
 * @since 2020/1/15 23:07
 **/
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MlogLog {
    /**
     * 描述
     *
     * @return 描述信息
     */
    String description() default "";
}
