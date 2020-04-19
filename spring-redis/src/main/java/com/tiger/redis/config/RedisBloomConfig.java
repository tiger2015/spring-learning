package com.tiger.redis.config;

import io.rebloom.client.Client;
import io.rebloom.client.ClusterClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.util.Pool;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Configuration
@PropertySource(value = {"classpath:application.properties", "${user.dir}/application.properties", "${user.dir}/config/application.properties"}, ignoreResourceNotFound = true)
@Import(RedisPoolConfig.class)
public class RedisBloomConfig {

    // 单节点
    private String host;
    private int port;

    // 集群
    private Set<HostAndPort> clusterHostAndPort = new HashSet<>();
    private int soTimeout; // socket超时
    private int maxAttempts; // 最大尝试次数

    // 通用配置
    private String password;
    private int connectionTimeout; // 连接超时


    @Bean("bloomClient")
    @Profile("standalone")
    public Client redisBloomClient(JedisPoolConfig jedisPoolConfig) {
        Pool<Jedis> pool = new JedisPool(jedisPoolConfig, host, port, soTimeout, password);
        Client client = new Client(pool);
        return client;
    }

    @Bean("bloomClusterClient")
    @Profile("cluster")
    public ClusterClient redisBloomClusterClient(JedisPoolConfig jedisPoolConfig) {
        ClusterClient clusterClient = new ClusterClient(clusterHostAndPort, connectionTimeout, soTimeout, maxAttempts, password, jedisPoolConfig);
        return clusterClient;
    }

    @Value("${redis.host:127.0.0.1}")
    public void setHost(String host) {
        this.host = host;
    }

    @Value("${redis.port:6379}")
    public void setPort(int port) {
        this.port = port;
    }

    @Value("${redis.cluster.nodes:127.0.0.1:6379}")
    public void setRedisNodes(String redisNodes) {
        String[] split = redisNodes.split(",");
        for (String node : split) {
            String[] subSplit = node.split(":");
            clusterHostAndPort.add(new HostAndPort(subSplit[0], Integer.parseInt(subSplit[1].trim())));
        }
    }

    @Value("${redis.password:}")
    public void setPassword(String password) {
        this.password = password;
    }

    @Value("${redis.connection.timeout:3000}")
    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    @Value("${redis.cluster.socketTimeout:3000}")
    public void setSoTimeout(int soTimeout) {
        this.soTimeout = soTimeout;
    }

    @Value("${redis.cluster.maxAttempts:5}")
    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }
}
