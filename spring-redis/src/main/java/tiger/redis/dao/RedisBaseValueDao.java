package tiger.redis.dao;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisBaseValueDao
 * @Description TODO
 * @Author tiger
 * @Date 2019/12/28 22:11
 * @Version 1.0
 **/
public interface RedisBaseValueDao<K, V> {
    void set(RedisTemplate<K, V> redisTemplate, K key, V value);

    V get(RedisTemplate<K, V> redisTemplate, K key);

    void multiSet(RedisTemplate<K, V> redisTemplate, Map<K, V> values);

    List<V> multiGet(RedisTemplate<K, V> redisTemplate, List<K> keys);

    void setKeyWithDuration(RedisTemplate<K, V> redisTemplate, K key, V value, long timeout, TimeUnit timeUnit);

    Boolean setIfAbsent(RedisTemplate<K, V> redisTemplate, K key, V value);

    Boolean setIfAbsent(RedisTemplate<K, V> redisTemplate, K key, V value, long timeout, TimeUnit timeUnit);
}
