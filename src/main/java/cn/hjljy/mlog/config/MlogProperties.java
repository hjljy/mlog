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
     * 缓存实现
     * memory （默认）
     * redis
     */
    private String cache = "memory";

    /**
     * 工作目录 默认是 /home/mlog/
     */
    private String workDir ="/home/mlog/";
}
