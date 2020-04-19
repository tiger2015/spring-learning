package tiger.redis.dao;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisBaseListDaoImpl
 * @Description TODO
 * @Author tiger
 * @Date 2019/12/28 22:14
 * @Version 1.0
 **/
@Component
public class RedisBaseListDaoImpl<K, V>  implements RedisBaseListDao<K, V> {
    @Override
    public Long lpush(RedisTemplate<K, V> redisTemplate, K key, V... values) {
        ListOperations<K, V> listOperations = redisTemplate.opsForList();
        return listOperations.leftPushAll(key, values);
    }

    @Override
    public Long rpush(RedisTemplate<K, V> redisTemplate, K key, V... values) {
        ListOperations<K, V> listOperations = redisTemplate.opsForList();
        return listOperations.rightPushAll(key, values);
    }

    @Override
    public V lpop(RedisTemplate<K, V> redisTemplate, K key) {
        ListOperations<K, V> listOperations = redisTemplate.opsForList();
        return listOperations.leftPop(key);
    }

    @Override
    public V lpop(RedisTemplate<K, V> redisTemplate, K key, V value, long timeout, TimeUnit timeUnit) {
        ListOperations<K, V> listOperations = redisTemplate.opsForList();
        return listOperations.leftPop(key, timeout, timeUnit);
    }

    @Override
    public V rpop(RedisTemplate<K, V> redisTemplate, K key) {
        ListOperations<K, V> listOperations = redisTemplate.opsForList();
        return listOperations.rightPop(key);
    }

    @Override
    public V rpop(RedisTemplate<K, V> redisTemplate, K key, V value, long timeout, TimeUnit timeUnit) {
        ListOperations<K, V> listOperations = redisTemplate.opsForList();
        return listOperations.rightPop(key, timeout, timeUnit);
    }

    @Override
    public List<V> range(RedisTemplate<K, V> redisTemplate, K key, long start, long end) {
        ListOperations<K, V> listOperations = redisTemplate.opsForList();
        return listOperations.range(key, start, end);
    }
}
