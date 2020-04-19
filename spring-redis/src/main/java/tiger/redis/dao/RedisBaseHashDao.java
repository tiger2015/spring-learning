package tiger.redis.dao;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Map;

/**
 * @ClassName RedisBaseHashDao
 * @Description TODO
 * @Author tiger
 * @Date 2019/12/28 22:13
 * @Version 1.0
 **/
public interface RedisBaseHashDao<K, V, HK, HV> {
    void hmset(RedisTemplate<K, V> redisTemplate, K key, Map<HK, HV> map);

    Map<HK, HV> hget(RedisTemplate<K, V> redisTemplate, K key, List<HK> hkList);

    Long hdel(RedisTemplate<K, V> redisTemplate, K key, HK... hks);
}
