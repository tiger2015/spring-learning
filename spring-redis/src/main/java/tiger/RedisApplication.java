package tiger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import tiger.redis.config.RedisTemplateConfig;
import tiger.redis.dao.redis.RedisBaseSetDao;
import tiger.redis.dao.redis.RedisBaseSetDaoImpl;

/**
 * Hello world!
 */
@Slf4j
public class RedisApplication {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "standalone");
        ApplicationContext context = new AnnotationConfigApplicationContext(RedisTemplateConfig.class);
        StringRedisTemplate redisTemplate = context.getBean(StringRedisTemplate.class);
        RedisBaseSetDao<String, String> redisBaseSetDao = context.getBean(RedisBaseSetDaoImpl.class);
        Long test = redisBaseSetDao.sadd(redisTemplate, "test", "1");
        log.info("result:{}", test);

    }
}
