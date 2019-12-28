package tiger.redis.dao.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @ClassName RedisBaseValueDaoImpl
 * @Description TODO
 * @Author tiger
 * @Date 2019/12/28 22:12
 * @Version 1.0
 **/
@Component
public class RedisBaseValueDaoImpl<K,V> implements RedisBaseValueDao<K,V> {
    @Override
    public void set(RedisTemplate<K, V> redisTemplate, K key, V value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public V get(RedisTemplate<K, V> redisTemplate, K key) {
        return redisTemplate.opsForValue().get(key);
    }

}
