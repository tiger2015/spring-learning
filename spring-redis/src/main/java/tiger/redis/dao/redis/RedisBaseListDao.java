package tiger.redis.dao.redis;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisBaseListDao
 * @Description TODO
 * @Author tiger
 * @Date 2019/12/28 22:14
 * @Version 1.0
 **/
public interface RedisBaseListDao<K, V> {
    Long lpush(RedisTemplate<K, V> redisTemplate, K key, V... values);

    Long rpush(RedisTemplate<K, V> redisTemplate, K key, V... values);

    V lpop(RedisTemplate<K, V> redisTemplate, K key);

    V lpop(RedisTemplate<K, V> redisTemplate, K key, V value, long timeout, TimeUnit timeUnit);

    V rpop(RedisTemplate<K, V> redisTemplate, K key);

    V rpop(RedisTemplate<K, V> redisTemplate, K key, V value, long timeout, TimeUnit timeUnit);

    List<V> range(RedisTemplate<K, V> redisTemplate, K key, long start, long end);

}
