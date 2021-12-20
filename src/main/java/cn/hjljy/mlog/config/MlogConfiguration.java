package cn.hjljy.mlog.config;

import cn.hjljy.mlog.cache.CacheStore;
import cn.hjljy.mlog.cache.InMemoryCacheStore;
import cn.hjljy.mlog.cache.JsonCacheStore;
import cn.hjljy.mlog.cache.RedisCacheStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * mlog项目配置
 *
 * @author hjljy
 * @date 2021/10/14
 */
@Slf4j
@Configuration
public class MlogConfiguration {

    private final MlogProperties mlogProperties;

    public MlogConfiguration(MlogProperties mlogProperties) {
        this.mlogProperties = mlogProperties;
    }

    /**
     * 初始化系统缓存存储,默认内存存储，可选redis
     * @param redisConnectionFactory redis链接信息
     * @return JsonCacheStore
     */
    @Bean
    @ConditionalOnMissingBean
    JsonCacheStore cacheStore(RedisConnectionFactory redisConnectionFactory) {
        JsonCacheStore cacheStore;
        switch (mlogProperties.getCache()) {
            case "redis":
                cacheStore = new RedisCacheStore(redisConnectionFactory);
                break;
            case "memory":
            default:
                cacheStore = new InMemoryCacheStore();
                break;
        }
        return cacheStore;
    }
}
