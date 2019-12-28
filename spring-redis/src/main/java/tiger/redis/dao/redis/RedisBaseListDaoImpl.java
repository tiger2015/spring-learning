package tiger.redis.dao.redis;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName RedisBaseListDaoImpl
 * @Description TODO
 * @Author tiger
 * @Date 2019/12/28 22:14
 * @Version 1.0
 **/
@Component
public class RedisBaseListDaoImpl<K, V> implements RedisBaseListDao<K, V> {
    @Override
    public Long lpush(RedisTemplate<K, V> redisTemplate, K key, V... values) {
        ListOperations<K, V> listOperations = redisTemplate.opsForList();
        return listOperations.leftPushAll(key, values);
    }

    @Override
    public V lpop(RedisTemplate<K, V> redisTemplate, K key) {
        ListOperations<K, V> listOperations = redisTemplate.opsForList();
        return listOperations.leftPop(key);
    }

    @Override
    public Long rpush(RedisTemplate<K, V> redisTemplate, K key, V... values) {
        ListOperations<K, V> listOperations = redisTemplate.opsForList();
        return listOperations.rightPushAll(key, values);
    }

    @Override
    public V rpop(RedisTemplate<K, V> redisTemplate, K key) {
        ListOperations<K, V> listOperations = redisTemplate.opsForList();
        return listOperations.rightPop(key);
    }

    @Override
    public List<V> lrange(RedisTemplate<K, V> redisTemplate, K key, long start, long end) {
        ListOperations<K, V> listOperations = redisTemplate.opsForList();
        return listOperations.range(key, start, end);
    }
}
