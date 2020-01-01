package tiger.redis.dao.redis;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisBaseCommonDao
 * @Description TODO
 * @Author tiger
 * @Date 2020/1/1 21:58
 * @Version 1.0
 **/
public interface RedisBaseCommonDao<K, V> {

    Boolean exprire(RedisTemplate<K, V> redisTemplate, K key, long timeout, TimeUnit timeUnit);

    Boolean hashKey(RedisTemplate<K, V> redisTemplate, K key);

    Long delete(RedisTemplate<K, V> redisTemplatem, K... keys);

    Long getExpire(RedisTemplate<K,V> redisTemplate, K key);
}
