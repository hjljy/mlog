package cn.hjljy.mlog.config;

import cn.hjljy.mlog.config.intersepter.MlogAuthConfigInterseptor;
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
    MlogAuthConfigInterseptor getMlogAuthConfigInterseptor() {
        return new MlogAuthConfigInterseptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getMlogAuthConfigInterseptor()).addPathPatterns("/mlog/**");
    }
}
