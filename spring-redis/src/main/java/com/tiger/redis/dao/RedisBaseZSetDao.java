package com.tiger.redis.dao;

import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.util.Map;

/**
 * @ClassName RedisBaseZSetDao
 * @Description TODO
 * @Author tiger
 * @Date 2019/12/28 22:15
 * @Version 1.0
 **/
public interface RedisBaseZSetDao<K, V> {
    Long zadd(RedisTemplate<K, V> redisTemplate, K key, Map<V, Double> members);

    Long zrem(RedisTemplate<K, V> redisTemplate, K key, V... values);

    Double zincr(RedisTemplate<K, V> redisTemplate, K key, V value, double increment);

    Map<V, Double> zscan(RedisTemplate<K, V> redisTemplate, K key, long count) throws IOException;

    Long zcard(RedisTemplate<K,V> redisTemplate, K key);
}
