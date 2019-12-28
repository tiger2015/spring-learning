package tiger.redis.dao.redis;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName RedisBaseSetDaoImpl
 * @Description TODO
 * @Author tiger
 * @Date 2019/12/28 22:15
 * @Version 1.0
 **/
@Component
public class RedisBaseSetDaoImpl<K, V> implements RedisBaseSetDao<K, V> {
    @Override
    public Long sadd(RedisTemplate<K, V> redisTemplate, K key, V... values) {
        SetOperations<K, V> setOperations = redisTemplate.opsForSet();
       return setOperations.add(key, values);
    }

    @Override
    public Long srem(RedisTemplate<K, V> redisTemplate, K key, V... values) {
        SetOperations<K, V> setOperations = redisTemplate.opsForSet();
       return setOperations.remove(key, values);
    }

    @Override
    public Long scard(RedisTemplate<K, V> redisTemplate, K key) {
        SetOperations<K, V> setOperations = redisTemplate.opsForSet();
        return setOperations.size(key);
    }

    @Override
    public Set<V> sscan(RedisTemplate<K, V> redisTemplate, K key, int count) throws IOException {
        SetOperations<K, V> setOperations = redisTemplate.opsForSet();
        ScanOptions.ScanOptionsBuilder builder = new ScanOptions.ScanOptionsBuilder();
        builder.count(count);
        Set<V> result = new HashSet<>();
        Cursor<V> cursor = null;
        try {
            cursor = setOperations.scan(key, builder.build());
            while (cursor.hasNext()) {
                result.add(cursor.next());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return result;
    }
}
