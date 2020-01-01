package tiger.redis.dao.redis;

import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName RedisBaseZSetDaoImpl
 * @Description TODO
 * @Author tiger
 * @Date 2019/12/28 22:15
 * @Version 1.0
 **/
@Component
public class RedisBaseZSetDaoImpl<K, V> implements RedisBaseZSetDao<K, V> {
    @Override
    public Long zadd(RedisTemplate<K, V> redisTemplate, K key, Map<V, Double> members) {
        ZSetOperations<K, V> zSetOperations = redisTemplate.opsForZSet();
        Set<ZSetOperations.TypedTuple<V>> tuples = new HashSet<>();
        members.forEach((k, v) -> {
            tuples.add(new DefaultTypedTuple<>(k, v));
        });
        return zSetOperations.add(key, tuples);
    }

    @Override
    public Long zrem(RedisTemplate<K, V> redisTemplate, K key, V... values) {
        ZSetOperations<K, V> zSetOperations = redisTemplate.opsForZSet();
       return zSetOperations.remove(key, values);
    }

    @Override
    public Double zincr(RedisTemplate<K, V> redisTemplate, K key, V value, double increment) {
        ZSetOperations<K, V> zSetOperations = redisTemplate.opsForZSet();
        return zSetOperations.incrementScore(key, value, increment);
    }

    @Override
    public Map<V, Double> zscan(RedisTemplate<K, V> redisTemplate, K key, long count) throws IOException {
        ZSetOperations<K, V> zSetOperations = redisTemplate.opsForZSet();
        ScanOptions.ScanOptionsBuilder builder = new ScanOptions.ScanOptionsBuilder();
        builder.count(count);
        Cursor<ZSetOperations.TypedTuple<V>> cursor = null;
        Map<V, Double> result = new HashMap<>();
        try {
            cursor = zSetOperations.scan(key, builder.build());
            while (cursor.hasNext()) {
                ZSetOperations.TypedTuple<V> next = cursor.next();
                result.put(next.getValue(), next.getScore());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return result;
    }

    @Override
    public Long zcard(RedisTemplate<K, V> redisTemplate, K key) {
        return redisTemplate.opsForZSet().zCard(key);
    }
}
