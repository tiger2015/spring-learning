package com.tiger.redis.distributelock;

import com.tiger.redis.dao.RedisBaseCommonDao;
import com.tiger.redis.dao.RedisBaseCommonDaoImpl;
import com.tiger.redis.dao.RedisBaseValueDao;
import com.tiger.redis.dao.RedisBaseValueDaoImpl;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @ClassName RedisLock
 * @Description TODO
 * @Author tiger
 * @Date 2020/1/1 22:26
 * @Version 1.0
 **/
public class RedisLock {

    private String lockName;

    private RedisTemplate<String, String> redisTemplate;
    private RedisBaseValueDao redisBaseValueDao;
    private RedisBaseCommonDao redisBaseCommonDao;

    public RedisLock(RedisTemplate<String, String> redisTemplate) {
        this(redisTemplate, "lock");
    }

    public RedisLock(RedisTemplate<String, String> redisTemplate, String lockName) {
        this.redisBaseValueDao = new RedisBaseValueDaoImpl();
        this.redisBaseCommonDao = new RedisBaseCommonDaoImpl();
        this.redisTemplate = redisTemplate;
        this.lockName = lockName;
    }

    public Boolean tryLock() {
        while (true) {
            // 如果返回true，则是获取到锁
            Boolean lock = this.redisBaseValueDao.setIfAbsent(redisTemplate, lockName, "lock");
            if (lock) {
                return true;
            }
        }
    }

    public void unlock(){
        // 释放锁
        this.redisBaseCommonDao.delete(redisTemplate, lockName);
    }



}
