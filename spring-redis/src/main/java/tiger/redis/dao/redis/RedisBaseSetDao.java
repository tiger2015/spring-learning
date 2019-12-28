package tiger.redis.dao.redis;

import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.util.Set;

/**
 * @ClassName RedisBaseSetDao
 * @Description TODO
 * @Author tiger
 * @Date 2019/12/28 22:14
 * @Version 1.0
 **/
public interface RedisBaseSetDao<K, V> {
    Long sadd(RedisTemplate<K, V> redisTemplate, K key, V... values);

    Long srem(RedisTemplate<K, V> redisTemplate, K key, V... values);

    Long scard(RedisTemplate<K, V> redisTemplate, K key);

    Set<V> sscan(RedisTemplate<K, V> redisTemplate, K key, int count) throws IOException;
}
