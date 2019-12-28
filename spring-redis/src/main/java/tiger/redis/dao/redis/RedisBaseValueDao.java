package tiger.redis.dao.redis;

import org.springframework.data.redis.core.RedisTemplate;

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
}
