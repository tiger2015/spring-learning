package tiger.redis.dao.redis;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName RedisBaseHashDaoImpl
 * @Description TODO
 * @Author tiger
 * @Date 2019/12/28 22:13
 * @Version 1.0
 **/
@Component
public class RedisBaseHashDaoImpl<K, V, HK, HV> implements RedisBaseHashDao<K, V, HK, HV> {
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
    public Long hdel(RedisTemplate<K, V> redisTemplate, K key, HK... hks) {
        HashOperations<K, HK, HV> hashOperations = redisTemplate.opsForHash();
       return hashOperations.delete(key, hks);
    }
}
