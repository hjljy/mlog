package cn.hjljy.mlog.cache;

import cn.hjljy.mlog.common.utils.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * json数据缓存
 *
 * @author hjljy
 * @date 2021/10/14
 */
@Slf4j
public abstract class JsonCacheStore extends AbstractCacheStore<String> {

    @SuppressWarnings("unchecked")
    protected Optional<CacheWrapper<String>> json2CacheWrapper(String json) {
        CacheWrapper<String> cacheWrapper = JacksonUtil.string2Obj(json, CacheWrapper.class);
        return Optional.ofNullable(cacheWrapper);
    }

    protected String cacheWrapper2Json(CacheWrapper<String> cacheWrapper) {
        return JacksonUtil.obj2String(cacheWrapper);
    }
}
