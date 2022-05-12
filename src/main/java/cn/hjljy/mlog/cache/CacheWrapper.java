package cn.hjljy.mlog.cache;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 针对缓存数据的封装
 * 实际保存到缓存当中的具体数据信息
 *
 * @author hjljy
 * @date 2021/09/10
 */
@Data
class CacheWrapper<T> implements Serializable {
    /**
     * 缓存的KEY
     */
    private String key;

    /**
     * 缓存的数据
     */
    private T data;

    /**
     * 过期时间 如果为null 表示永不过期
     */
    private LocalDateTime expireTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    Boolean isExpire() {
        return null != expireTime && expireTime.isBefore(LocalDateTime.now());
    }
}
