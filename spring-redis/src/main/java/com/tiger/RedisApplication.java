package com.tiger;

import com.tiger.redis.config.RedisBloomConfig;
import com.tiger.redis.config.RedisTemplateConfig;
import com.tiger.redis.dao.*;
import io.rebloom.client.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.IOException;
import java.util.Set;

/**
 * Hello world!
 */
@Slf4j
public class RedisApplication {
    public static void main(String[] args) throws IOException {
        System.setProperty("spring.profiles.active", "standalone");

        //================
        ApplicationContext context = new AnnotationConfigApplicationContext(RedisTemplateConfig.class);


        /**
        StringRedisTemplate redisTemplate = context.getBean(StringRedisTemplate.class);
        RedisBaseSetDao<String, String> redisBaseSetDao = context.getBean(RedisBaseSetDaoImpl.class);
        Long size = redisBaseSetDao.scard(redisTemplate, "set");

        Set<String> test = redisBaseSetDao.sscan(redisTemplate, "set", 500);
        log.info("size:{}", size);
        log.info("test:{}", test);

        RedisBaseCommonDao redisBaseCommonDao = context.getBean(RedisBaseCommonDaoImpl.class);

        redisBaseCommonDao.redisPipeline(redisTemplate, connection -> {
            connection.openPipeline();  // 打开管道
            log.info("open pipeline");


            connection.closePipeline(); // 关闭管道
            log.info("close pipeline");
            return null;
        });

        ((AnnotationConfigApplicationContext) context).close();
        **/

        //=====================
        //====== bloom filter test
        /**
        ApplicationContext context = new AnnotationConfigApplicationContext(RedisBloomConfig.class);
        Client client = context.getBean(Client.class);
        client.add("users","user3");
        log.info("exists:{}", client.exists("users","user123"));
         **/


        RedisListOperations<String,String> listOperations = context.getBean(RedisListOperations.class);
        listOperations.add("list", "l1");
    }
}
