package cn.hjljy.mlog.cache;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 复述,缓存存储
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
    protected Optional<CacheWrapper<String>> getInternal(String key) {
        String value = "";
        try {
            value = redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            log.warn("获取缓存信息失败，key:{},失败原因：{}", key, e.getMessage());
        }
        return StrUtil.isBlank(value) ? Optional.empty() : json2CacheWrapper(value);
    }

    @Override
    protected void putInternal(String key, CacheWrapper<String> cacheWrapper) {
        try {
            LocalDateTime expireTime = cacheWrapper.getExpireTime();
            if (expireTime != null) {
                Duration between = LocalDateTimeUtil.between(cacheWrapper.getCreateTime(), expireTime);
                redisTemplate.opsForValue().set(key, cacheWrapper2Json(cacheWrapper), between);
            } else {
                redisTemplate.opsForValue().set(key, cacheWrapper.getData());
            }
        } catch (Exception e) {
            log.warn("存取缓存信息失败，key:{},失败原因：{}", key, e.getMessage());
        }
    }

    @Override
    protected Boolean putInternalIfAbsent(String key, CacheWrapper<String> cacheWrapper) {
        Boolean hasKey = redisTemplate.hasKey(key);
        if (null != hasKey && hasKey) {
            return false;
        }
        putInternal(key, cacheWrapper);
        return true;
    }

    @Override
    public void delete(String key) {
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
