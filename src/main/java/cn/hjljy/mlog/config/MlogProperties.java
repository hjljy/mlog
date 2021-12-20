package cn.hjljy.mlog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * mlog属性
 *
 * @author hjljy
 * @date 2021/10/14
 */
@Data
@Component
@ConfigurationProperties("mlog")
public class MlogProperties {
    /**
     * cache store impl
     * memory
     * redis
     */
    private String cache = "memory";
}
