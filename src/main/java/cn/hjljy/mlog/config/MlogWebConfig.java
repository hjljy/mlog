package cn.hjljy.mlog.config;

import cn.hjljy.mlog.config.interceptor.AuthConfigInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * mlog web相关配置信息
 *
 * @author 海加尔金鹰 www.hjljy.cn
 * @email hjljy@outlook.com
 * @date 2021/10/14
 */
@Configuration
public class MlogWebConfig implements WebMvcConfigurer {

    @Bean
    AuthConfigInterceptor getMlogAuthConfigInterceptor() {
        return new AuthConfigInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //权限拦截器 拦截需要鉴权的请求  所有admin开头的都需要拦截
        registry.addInterceptor(getMlogAuthConfigInterceptor()).addPathPatterns("/admin/**").order(124);
    }
}
