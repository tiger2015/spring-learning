package com.tiger.redis.dao;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisBaseValueDaoImpl
 * @Description TODO
 * @Author tiger
 * @Date 2019/12/28 22:12
 * @Version 1.0
 **/
@Component
public class RedisBaseValueDaoImpl<K, V> implements RedisBaseValueDao<K, V> {
    @Override
    public void set(RedisTemplate<K, V> redisTemplate, K key, V value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public V get(RedisTemplate<K, V> redisTemplate, K key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void multiSet(RedisTemplate<K, V> redisTemplate, Map<K, V> values) {
        redisTemplate.opsForValue().multiSet(values);
    }

    @Override
    public List<V> multiGet(RedisTemplate<K, V> redisTemplate, List<K> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    @Override
    public void setKeyWithDuration(RedisTemplate<K, V> redisTemplate, K key, V value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    @Override
    public Boolean setIfAbsent(RedisTemplate<K, V> redisTemplate, K key, V value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    @Override
    public Boolean setIfAbsent(RedisTemplate<K, V> redisTemplate, K key, V value, long timeout, TimeUnit timeUnit) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, timeout, timeUnit);
    }
}
