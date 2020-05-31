package com.tiger.redis.dao;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RedisListOperations<K, V> {


    // 由于DefaultListOperations类非public，包访问权限,不能通过Spring的Autowired、Quarlify方式注入
    @Resource(name = "stringRedisTemplate")
    private ListOperations<K, V> listOperations;

    public void add(K key, V value) {
        listOperations.leftPush(key, value);
    }

}
