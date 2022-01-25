package cn.hjljy.mlog.config;

import cn.hjljy.mlog.common.constants.Constant;
import cn.hjljy.mlog.common.utils.MlogUtils;
import cn.hjljy.mlog.config.interceptor.AuthConfigInterceptor;
import cn.hjljy.mlog.config.interceptor.MlogPathInterceptor;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.CacheControl;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    private static final String UPLOAD_URL = MlogUtils.ensureBoth( Constant.UPLOAD_PREFIX,"/") + "**";
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

       //无需拦截的路径  静态资源，主题信息，后台请求
        List<String> excludePath = new ArrayList<>();
        excludePath.add("/themes/**");
        excludePath.add("/admin/**");
        excludePath.add(UPLOAD_URL);
        excludePath.add("/login");
        excludePath.add("/error");
        excludePath.add("/**/401.html");
        excludePath.add("/**/404.html");
        excludePath.add("/**/500.html");
        excludePath.add("/**/**.js");
        excludePath.add("/**/**.css");
        excludePath.add("/**/**.ico");
        excludePath.add("/**/**.png");
        excludePath.add("/**/**.jpg");
        //拦截所有路径
        registry.addInterceptor(getMlogPathInterceptor()).excludePathPatterns(excludePath).addPathPatterns("/**").order(12);
    }

    /**
     * Configuring static resource path
     *
     * @param registry registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String workDir = FILE_PROTOCOL + mlogProperties.getWorkDir();


        // register /themes/** resource handler.
        registry.addResourceHandler("/themes/**")
                .addResourceLocations("classpath:/templates/themes/");

        registry.addResourceHandler(UPLOAD_URL)
                .setCacheControl(CacheControl.maxAge(7L, TimeUnit.DAYS))
                .addResourceLocations(workDir +Constant.UPLOAD_PREFIX);
    }

    /**
     * 允许跨域请求
     *
     */
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // 设置所有地址的请求都可以
        config.addAllowedOriginPattern("*");

        // 设置为允许所有请求头信息
        config.addAllowedHeader("*");

        // 设置为支持所有请求方式
        config.addAllowedMethod("*");

        // 设置所有的请求路径都可以访问
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        //设置优先级为最高
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
