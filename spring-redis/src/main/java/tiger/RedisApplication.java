package tiger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import tiger.redis.config.RedisTemplateConfig;
import tiger.redis.dao.redis.RedisBaseCommonDao;
import tiger.redis.dao.redis.RedisBaseCommonDaoImpl;
import tiger.redis.dao.redis.RedisBaseSetDao;
import tiger.redis.dao.redis.RedisBaseSetDaoImpl;

import java.io.IOException;
import java.util.Set;

/**
 * Hello world!
 */
@Slf4j
public class RedisApplication {
    public static void main(String[] args) throws IOException {
        System.setProperty("spring.profiles.active", "standalone");
        ApplicationContext context = new AnnotationConfigApplicationContext(RedisTemplateConfig.class);
        StringRedisTemplate redisTemplate = context.getBean(StringRedisTemplate.class);
        RedisBaseSetDao<String, String> redisBaseSetDao = context.getBean(RedisBaseSetDaoImpl.class);
        // Long test = redisBaseSetDao.sadd(redisTemplate, "test", "5", "5");
        // log.info("result:{}", test);
        Long size = redisBaseSetDao.scard(redisTemplate, "test");

        Set<String> test = redisBaseSetDao.sscan(redisTemplate, "test", 500);
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
    }
}
