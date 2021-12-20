package cn.hjljy.mlog.cache;

import org.springframework.lang.NonNull;

import java.time.temporal.ChronoUnit;
import java.util.Optional;


/**
 * 缓存存储通用接口定义
 * 所有缓存的key类型均为String类型
 * @author hjljy
 * @date 2021/09/10
 */
public interface CacheStore<V> {


    /**
     * 获取缓存的数据
     *
     * @param key key不能为空
     * @return {@link Optional}<{@link V}>
     */
    @NonNull
    Optional<V> get(@NonNull String key);


    /**
     * 保存缓存数据
     *
     * @param key      key不能为空
     * @param value    value不能为空
     * @param timeout  超时时间  如果为0表示永不过期 ，不能为负数 ，否者抛出异常
     * @param timeUnit 时间单位 不能为空
     */
    void put(@NonNull String key, @NonNull V value, long timeout, @NonNull ChronoUnit timeUnit) ;


    /**
     * 保存缓存数据   永不过期
     *
     * @param key   key不能为空
     * @param value value不能为空
     */
    void put(@NonNull String key, @NonNull V value);


    /**
     * 保存缓存之前判断缓存是否存在，不存在就保存，否则不保存
     *
     * @param key      key不能为空
     * @param value    value不能为空
     * @param timeout  超时时间  如果为0表示永不过期 ，不能为负数 ，否者抛出异常
     * @param timeUnit 时间单位 不能为空
     * @return {@link Boolean}
     */
    Boolean putIfAbsent(@NonNull String key, @NonNull V value, long timeout, @NonNull ChronoUnit timeUnit) ;

    /**
     * 删除缓存
     *
     * @param key key不能为空
     */
    void delete(@NonNull String key);

}
