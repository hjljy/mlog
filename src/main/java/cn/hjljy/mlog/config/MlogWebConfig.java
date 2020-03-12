package cn.hjljy.mlog.config;

import cn.hjljy.mlog.config.interceptor.MlogAuthConfigInterceptor;
import cn.hjljy.mlog.config.interceptor.MlogPathInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 海加尔金鹰 www.hjljy.cn
 * @version V1.0
 * @email hjljy@outlook.com
 * @description: 配置信息
 * @since 2020/1/22 12:16
 **/
@Configuration
public class MlogWebConfig implements WebMvcConfigurer {

    @Bean
    MlogAuthConfigInterceptor getMlogAuthConfigInterceptor() {
        return new MlogAuthConfigInterceptor();
    }

    @Bean
    MlogPathInterceptor getMlogPathInterceptor() {
        return new MlogPathInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(getMlogPathInterceptor()).addPathPatterns("/**").order(123);
        registry.addInterceptor(getMlogAuthConfigInterceptor()).addPathPatterns("/mlog/**").order(124);
    }
}
