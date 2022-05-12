package cn.hjljy.mlog.cache;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

/**
 * 抽象的数据缓存
 * 抽象的数据缓存封装
 *
 * @author hjljy
 * @date 2021/09/10
 */
@Slf4j
public abstract class AbstractCacheStore<V> implements CacheStore<V> {

    /**
     * 获取内部保存的封装后的数据
     *
     * @param key key不能为空
     * @return {@link Optional}<{@link CacheWrapper}<{@link V}>>
     */
    protected abstract Optional<CacheWrapper<V>> getInternal(@NonNull String key);


    /**
     * 将封装好的缓存数据保存到缓存当中
     *
     * @param key          关键
     * @param cacheWrapper 缓存包装
     */
    protected abstract void putInternal(@NonNull String key, @NonNull CacheWrapper<V> cacheWrapper);


    /**
     * 保存缓存之前判断缓存是否存在，不存在就保存，否则不保存
     *
     * @param key          key 不能为空
     * @param cacheWrapper 封装好的缓存数据
     * @return {@link Boolean}
     */
    @NonNull
    protected abstract Boolean putInternalIfAbsent(@NonNull String key, @NonNull CacheWrapper<V> cacheWrapper);


    /**
     * 获取缓存的数据
     *
     * @param key key不能为空
     * @return {@link Optional}<{@link V}>
     */
    @Override
    public Optional<V> get(@NotNull String key) {
        Assert.hasText(key, "缓存的key不能为空");
        return getInternal(key).map(cacheWrapper -> {
            // 判断缓存是否过期
            if (cacheWrapper.isExpire()) {
                // 过期删除缓存  并返回null
                log.info("缓存key:{} 已过期,过期时间是:{}", key,cacheWrapper.getExpireTime());
                delete(key);
                return null;
            }
            return cacheWrapper.getData();
        });
    }
    /**
     * 保存缓存数据
     *
     * @param key      key不能为空
     * @param value    value不能为空
     * @param timeout  超时时间  如果为0表示永不过期 ，不能为负数 ，否者抛出异常
     * @param timeUnit 时间单位
     */
    @Override
    public void put(@NotNull String key, @NotNull V value, long timeout, ChronoUnit timeUnit) {
        putInternal(key, buildCacheWrapper(key, value, timeout, timeUnit));
    }
    /**
     * 保存缓存数据   永不过期
     *
     * @param key   key不能为空
     * @param value value不能为空
     */
    @Override
    public void put(@NotNull String key, @NotNull V value) {
        put(key,value,0,null);
    }

    /**
     * 保存缓存之前判断缓存是否存在，不存在就保存，否则不保存
     *
     * @param key      key不能为空
     * @param value    value不能为空
     * @param timeout  超时时间  如果为0表示永不过期 ，不能为负数 ，否者抛出异常
     * @param timeUnit 时间单位
     * @return {@link Boolean}
     */
    @Override
    public Boolean putIfAbsent(@NotNull String key, @NotNull V value, long timeout, ChronoUnit timeUnit) {
        return putInternalIfAbsent(key, buildCacheWrapper(key, value, timeout, timeUnit));
    }


    /**
     * 生成缓存的包装类
     *
     * @param key      key不能为空
     * @param value    值不能为空
     * @param timeout  超时时间  如果为0表示永不过期 ，不能为负数 ，否者抛出异常
     * @param timeUnit 时间单位
     * @return {@link CacheWrapper}<{@link V}>
     */
    @NonNull
    public CacheWrapper<V> buildCacheWrapper(@NonNull String key, @NonNull V value, long timeout,
                                              @Nullable ChronoUnit timeUnit) {
        Assert.notNull(value, "缓存的值不能为空");
        Assert.isTrue(timeout >= 0, "缓存超时时间不能为负数");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expireAt = null;
        if (timeout > 0 && timeUnit != null) {
            expireAt = now.plus(timeout, timeUnit);
        }
        CacheWrapper<V> cacheWrapper = new CacheWrapper<>();
        cacheWrapper.setCreateTime(now);
        cacheWrapper.setExpireTime(expireAt);
        cacheWrapper.setKey(key);
        cacheWrapper.setData(value);
        return cacheWrapper;
    }
}
