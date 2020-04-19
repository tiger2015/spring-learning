package tiger.redis.dao;

import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Map;

/**
 * @ClassName RedisBaseGeoDao
 * @Description TODO
 * @Author tiger
 * @Date 2019/12/28 22:16
 * @Version 1.0
 **/
public interface RedisBaseGeoDao<K, M> {

    public enum GEOSORT {
        ASC,
        DESC
    }

    Long geoAdd(RedisTemplate<K, M> redisTemplate, K key, Map<M, Point> points);

    Map<M, Point> geoHash(RedisTemplate<K, M> redisTemplate, K key, M... members);

    Long geoRem(RedisTemplate<K, M> redisTemplate, K key, M... members);

    List<RedisGeoCommands.GeoLocation<M>> radius(RedisTemplate<K, M> redisTemplate, K key, Point point, double radius, GEOSORT geosort);
}
