package tiger.redis.dao;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName RedisBaseDaoImpl
 * @Description TODO
 * @Author tiger
 * @Date 2019/12/27 22:20
 * @Version 1.0
 **/
public class RedisBaseDaoImpl<K, V, HK, HV> implements RedisBaseDao<K, V, HK, HV> {
    @Override
    public void set(RedisTemplate<K, V> redisTemplate, K key, V value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public V get(RedisTemplate<K, V> redisTemplate, K key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void hmset(RedisTemplate<K, V> redisTemplate, K key, Map<HK, HV> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    @Override
    public Map<HK, HV> hget(RedisTemplate<K, V> redisTemplate, K key, List<HK> hkList) {
        HashOperations<K, HK, HV> hashOperations = redisTemplate.opsForHash();
        List<HV> values = hashOperations.multiGet(key, hkList);
        Map<HK, HV> result = new HashMap<>();
        for (int i = 0; i < hkList.size(); i++) {
            result.put(hkList.get(i), values.get(i));
        }
        return result;
    }

    @Override
    public void hdel(RedisTemplate<K, V> redisTemplate, K key, HK... hks) {
        HashOperations<K, HK, HV> hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(key, hks);
    }


}
