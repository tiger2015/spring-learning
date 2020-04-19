package com.tiger.redis.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName RedisPoolConfig
 * @Description TODO
 * @Author tiger
 * @Date 2019/12/27 21:22
 * @Version 1.0
 **/
@Slf4j
@Configuration
@PropertySource(value = {"classpath:application.properties", "${user.dir}/application.properties", "${user.dir}/config/application.properties"}, ignoreResourceNotFound = true)
public class RedisPoolConfig {
    private int maxTotal;
    private int maxIdle;
    private int minIdle;
    private long maxWaitMillis;
    private boolean testOnBorrow;

    @Bean(name = "jedisPoolConfig")
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        return jedisPoolConfig;
    }

    @Value("${redis.pool.maxTotal:8}")
    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    @Value("${redis.pool.maxIdle:8}")
    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    @Value("${redis.pool.minIdle:0}")
    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    @Value("${redis.pool.maxWaitMillis:-1}")
    public void setMaxWaitMillis(long maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    @Value("${redis.pool.testOnBorrow:true}")
    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }
}
