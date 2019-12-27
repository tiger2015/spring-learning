package tiger.redis.dao;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Map;

/**
 * @ClassName RedisBaseDao
 * @Description TODO
 * @Author tiger
 * @Date 2019/12/27 22:15
 * @Version 1.0
 **/
public interface RedisBaseDao<K, V, HK, HV> {

    //====================String
    void set(RedisTemplate<K, V> redisTemplate, K key, V value);

    V get(RedisTemplate<K, V> redisTemplate, K key);
    //====================================================

    // ===============hash
    void hmset(RedisTemplate<K, V> redisTemplate, K key, Map<HK, HV> map);

    Map<HK, HV> hget(RedisTemplate<K, V> redisTemplate, K key, List<HK> hkList);

    void hdel(RedisTemplate<K,V> redisTemplate, K key, HK ...hks);
    // =================
}
