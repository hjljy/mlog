package cn.hjljy.mlog.cache;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 内存缓存存取
 *
 * @author hjljy
 * @date 2021-09-13
 */
public class InMemoryCacheStore extends JsonCacheStore {

    private static final ConcurrentHashMap<String, CacheWrapper<String>> CACHE_CONTAINER = new ConcurrentHashMap<>();

    @Override
    protected Optional<CacheWrapper<String>> getInternal(String key) {
        return Optional.ofNullable(CACHE_CONTAINER.get(key));
    }

    @Override
    protected void putInternal(String key, CacheWrapper<String> cacheWrapper) {
        CACHE_CONTAINER.put(key, cacheWrapper);
    }

    @Override
    protected Boolean putInternalIfAbsent(String key, CacheWrapper<String> cacheWrapper) {
        CacheWrapper<String> wrapper = CACHE_CONTAINER.get(key);
        //对象锁，保证线程安全
        synchronized (this) {
            if (null != wrapper) {
                putInternal(key, cacheWrapper);
                return true;
            }
            return false;
        }
    }

    @Override
    public void delete(String key) {
        CACHE_CONTAINER.remove(key);
    }
}
