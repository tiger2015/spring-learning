package tiger.redis.dao.redis;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisBaseCommonDaoImpl
 * @Description TODO
 * @Author tiger
 * @Date 2020/1/1 21:59
 * @Version 1.0
 **/
@Component
public  class RedisBaseCommonDaoImpl<K, V> implements RedisBaseCommonDao<K, V> {

    /**
     * 设置key过期
     * @param redisTemplate
     * @param key
     * @param timeout
     * @param timeUnit
     * @return
     */
    @Override
    public Boolean exprire(RedisTemplate<K, V> redisTemplate, K key, long timeout, TimeUnit timeUnit) {
        return redisTemplate.expire(key, timeout, timeUnit);
    }

    /**
     * 是否存在key
     * @param redisTemplate
     * @param key
     * @return
     */
    @Override
    public Boolean hashKey(RedisTemplate<K, V> redisTemplate, K key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除key
     * @param redisTemplate
     * @param keys
     * @return
     */
    @Override
    public Long delete(RedisTemplate<K, V> redisTemplate, K... keys) {
        return redisTemplate.delete(Arrays.asList(keys));
    }

    /**
     * 获取key的过期时间
     * @param redisTemplate
     * @param key
     * @return
     */
    @Override
    public Long getExpire(RedisTemplate<K, V> redisTemplate, K key) {
        return redisTemplate.getExpire(key);
    }

    @Override
    public List<Object> redisPipeline(RedisTemplate<K, V> redisTemplate, RedisCallback<Object> callback) {
       return redisTemplate.executePipelined(callback);
    }
}
