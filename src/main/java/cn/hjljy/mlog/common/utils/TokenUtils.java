package cn.hjljy.mlog.common.utils;

import cn.hjljy.mlog.cache.JsonCacheStore;
import cn.hjljy.mlog.common.constants.CacheConstant;
import cn.hjljy.mlog.config.TokenInfo;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

/**
 * 令牌跑龙套
 *
 * @author hjljy
 * @date 2021/10/15
 */
@Component
public class TokenUtils {
    @Resource
    JsonCacheStore jsonCacheStore;

    /**
     * 获取当前请求当中的token信息
     * @return {@link TokenInfo}
     */
    public TokenInfo get(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return get(request);
    }

    /**
     * 获取请求TOKEN INFO
     *
     * @param request 请求
     * @return {@link TokenInfo}
     */
    public TokenInfo get(HttpServletRequest request){
        return get(HttpServletRequestUtils.getAuthorization(request));
    }

    /**
     * 获取token对应详情
     *
     * @param token 令牌
     * @return {@link TokenInfo}
     */
    public TokenInfo get(String token) {
        Optional<String> optional = jsonCacheStore.get(CacheConstant.TOKEN_KEY + token);
        return optional.map(s -> JacksonUtil.string2Obj(s, TokenInfo.class)).orElse(null);
    }

    /**
     * 保存token到缓存当中
     *
     * @param key      token  不能为空
     * @param value    token value  不能为空
     * @param timeout  超时时间
     * @param timeUnit 时间单位
     */
    public void saveToken(@NonNull String key, @NonNull TokenInfo value, long timeout, @NonNull ChronoUnit timeUnit){
        jsonCacheStore.put(CacheConstant.TOKEN_KEY +key,JacksonUtil.obj2String(value),timeout,timeUnit);
    }


}
