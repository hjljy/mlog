package cn.hjljy.mlog.config;

import cn.hjljy.mlog.common.constants.Constant;
import cn.hjljy.mlog.common.utils.MlogUtils;
import cn.hjljy.mlog.config.interceptor.AuthConfigInterceptor;
import cn.hjljy.mlog.config.interceptor.MlogPathInterceptor;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * mlog web相关配置信息
 *
 * @author 海加尔金鹰 www.hjljy.cn
 * @email hjljy@outlook.com
 * @date 2021/10/14
 */
@Configuration
public class MlogWebConfig implements WebMvcConfigurer {
    private static final String FILE_PROTOCOL = "file:///";
    private final MlogProperties mlogProperties;

    public MlogWebConfig(MlogProperties mlogProperties) {
        this.mlogProperties = mlogProperties;
    }

    @Bean
    AuthConfigInterceptor getMlogAuthConfigInterceptor() {
        return new AuthConfigInterceptor();
    }

    @Bean
    MlogPathInterceptor getMlogPathInterceptor() {
        return new MlogPathInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //权限拦截器 拦截需要鉴权的请求  所有admin开头的都需要拦截
        registry.addInterceptor(getMlogAuthConfigInterceptor()).addPathPatterns("/admin/**").order(124);

        registry.addInterceptor(getMlogPathInterceptor()).addPathPatterns("/**").order(12);
    }

    /**
     * Configuring static resource path
     *
     * @param registry registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String workDir = FILE_PROTOCOL + mlogProperties.getWorkDir();
        String uploadUrlPattern = MlogUtils.ensureBoth( Constant.UPLOAD_PREFIX,"/") + "**";
        registry.addResourceHandler(uploadUrlPattern)
                .setCacheControl(CacheControl.maxAge(7L, TimeUnit.DAYS))
                .addResourceLocations(workDir +Constant.UPLOAD_PREFIX);
    }
}
