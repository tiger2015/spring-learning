package com.tiger.redis.config;

import io.lettuce.core.ClientOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RedisConfig
 * @Description TODO
 * @Author tiger
 * @Date 2019/12/27 21:20
 * @Version 1.0
 **/
@Configuration
@Import(RedisPoolConfig.class)
@PropertySource(value = {"classpath:application.properties", "${user.dir}/application.properties", "${user.dir}/config/application.properties"}, ignoreResourceNotFound = true)
@Slf4j
public class RedisConnectionConfig {

    private String password;

    // 单机
    private String host;
    private int port;

    // 集群
    private List<RedisNode> redisNodeList;

    // 哨兵
    private List<RedisNode> sentinelNodeList;
    private String master;

    @Bean
    public LettucePoolingClientConfiguration lettucePoolingClientConfiguration(JedisPoolConfig jedisPoolConfig) {
        LettucePoolingClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder()
                .poolConfig(jedisPoolConfig)
                .clientOptions(ClientOptions.builder().build())
        .build();
        return clientConfiguration;
    }


    @Bean
    @Profile("standalone")
    public LettuceConnectionFactory standaloneLettuceConnectionFactory(LettucePoolingClientConfiguration clientConfiguration) {
        RedisStandaloneConfiguration standaloneConfiguration = new RedisStandaloneConfiguration();
        standaloneConfiguration.setPort(port);
        standaloneConfiguration.setHostName(host);
        standaloneConfiguration.setPassword(RedisPassword.of(password));
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(standaloneConfiguration, clientConfiguration);
        lettuceConnectionFactory.afterPropertiesSet();
        return lettuceConnectionFactory;
    }

    @Bean
    @Profile("cluster")
    public LettuceConnectionFactory clusterLettuceConnectionFactory(LettucePoolingClientConfiguration clientConfiguration) {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        redisClusterConfiguration.setClusterNodes(redisNodeList);
        redisClusterConfiguration.setPassword(RedisPassword.of(password));
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisClusterConfiguration, clientConfiguration);
        lettuceConnectionFactory.afterPropertiesSet();
        return lettuceConnectionFactory;
    }


    @Bean
    @Profile("sentinel")
    public LettuceConnectionFactory sentinelLettuceConnectionFactory(LettucePoolingClientConfiguration clientConfiguration){
        RedisSentinelConfiguration sentinelConfiguration = new RedisSentinelConfiguration();
        sentinelConfiguration.setSentinels(sentinelNodeList); // 哨兵
        sentinelConfiguration.setMaster(master);
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(sentinelConfiguration, clientConfiguration);
        return lettuceConnectionFactory;
    }



    @Value("${redis.password:}")
    public void setPassword(String password) {
        this.password = password;
    }

    @Value("${redis.host:127.0.0.1}")
    public void setHost(String host) {
        this.host = host;
    }

    @Value("${redis.port:6379}")
    public void setPort(int port) {
        this.port = port;
    }

    @Value("${redis.cluster.nodes:}")
    public void setRedisNodeList(String nodes) {
        this.redisNodeList = new ArrayList<>();
        String[] split = nodes.split(",");
        for (String node : split) {
            String[] subSplit = node.split(":");
            this.redisNodeList.add(new RedisNode(subSplit[0], Integer.parseInt(subSplit[1].trim())));
        }
    }

    @Value("${redis.sentinel.master:master}")
    public void setMaster(String master) {
        this.master = master;
    }

    @Value("${redis.sentinel.nodes:")
    public void setSentinelNodeList(String nodes) {
        this.sentinelNodeList = new ArrayList<>();
        String[] split = nodes.split(",");
        for (String node : split) {
            String[] subSplit = node.split(":");
            this.sentinelNodeList.add(new RedisNode(subSplit[0], Integer.parseInt(subSplit[1].trim())));
        }
    }
}
