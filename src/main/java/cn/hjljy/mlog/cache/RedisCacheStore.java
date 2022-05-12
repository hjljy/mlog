package cn.hjljy.mlog.cache;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * redis缓存存储
 *
 * @author hjljy
 * @date 2021/10/14
 */
@Slf4j
public class RedisCacheStore extends JsonCacheStore {

    StringRedisTemplate redisTemplate;
    public RedisCacheStore(RedisConnectionFactory redisConnectionFactory) {
        initRedis(redisConnectionFactory);
    }


    @Override
    @NotNull
    protected  Optional<CacheWrapper<String>> getInternal(@NotNull String key) {
        String value = "";
        try {
            value = redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            log.warn("获取缓存信息失败，key:{},失败原因：{}", key, e.getMessage());
        }
        return StrUtil.isBlank(value) ? Optional.empty() : json2CacheWrapper(value);
    }

    @Override
    protected void putInternal(@NotNull String key, @NotNull CacheWrapper<String> cacheWrapper) {
        try {
            LocalDateTime expireTime = cacheWrapper.getExpireTime();
            if (expireTime != null) {
                Duration between = LocalDateTimeUtil.between(cacheWrapper.getCreateTime(), expireTime);
                redisTemplate.opsForValue().set(key, cacheWrapper2Json(cacheWrapper), between);
            } else {
                redisTemplate.opsForValue().set(key, cacheWrapper2Json(cacheWrapper));
            }
        } catch (Exception e) {
            log.warn("存取缓存信息失败，key:{},失败原因：{}", key, e.getMessage());
        }
    }

    @Override
    @NotNull
    protected Boolean putInternalIfAbsent(@NotNull String key, @NotNull CacheWrapper<String> cacheWrapper) {
        Boolean hasKey = redisTemplate.hasKey(key);
        if (null != hasKey && hasKey) {
            return false;
        }
        putInternal(key, cacheWrapper);
        return true;
    }

    @Override
    public void delete(@NotNull String key) {
        try {
            redisTemplate.delete(key);
        } catch (Exception e) {
            log.warn("清除缓存信息失败，key:{},失败原因：{}", key, e.getMessage());
        }
    }

    protected void initRedis(RedisConnectionFactory redisConnectionFactory) {
        if (redisTemplate == null) {
            redisTemplate = new StringRedisTemplate();
            redisTemplate.setConnectionFactory(redisConnectionFactory);
            redisTemplate.afterPropertiesSet();
        }
    }
}
