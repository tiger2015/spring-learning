package tiger.redis.dao.redis;

import org.springframework.data.geo.Circle;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @ClassName RedisBaseGeoDaoImpl
 * @Description TODO
 * @Author tiger
 * @Date 2019/12/28 22:16
 * @Version 1.0
 **/
@Component
public class RedisBaseGeoDaoImpl<K, M> implements RedisBaseGeoDao<K, M> {
    @Override
    public Long geoAdd(RedisTemplate<K, M> redisTemplate, K key, Map<M, Point> points) {
        GeoOperations<K, M> geoOperations = redisTemplate.opsForGeo();
        return geoOperations.add(key, points);
    }

    @Override
    public Map<M, Point> geoHash(RedisTemplate<K, M> redisTemplate, K key, M... members) {
        GeoOperations<K, M> geoOperations = redisTemplate.opsForGeo();
        List<Point> position = geoOperations.position(key, members);
        Map<M, Point> result = new HashMap<>();
        for (int i = 0; i < members.length; i++) {
            result.put(members[i], position.get(i));
        }
        return result;
    }

    @Override
    public Long geoRem(RedisTemplate<K, M> redisTemplate, K key, M... members) {
        GeoOperations<K, M> geoOperations = redisTemplate.opsForGeo();
        return geoOperations.remove(key, members);
    }

    @Override
    public List<RedisGeoCommands.GeoLocation<M>> radius(RedisTemplate<K, M> redisTemplate, K key, Point point, double radius, GEOSORT geosort) {
        GeoOperations<K, M> geoOperations = redisTemplate.opsForGeo();
        RedisGeoCommands.GeoRadiusCommandArgs geoRadiusCommandArgs = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs();
        if (geosort == GEOSORT.ASC) {
            geoRadiusCommandArgs.sortAscending();
        } else {
            geoRadiusCommandArgs.sortDescending();
        }
        GeoResults<RedisGeoCommands.GeoLocation<M>> geoResults = geoOperations.radius(key, new Circle(point, radius), geoRadiusCommandArgs);
        Iterator<GeoResult<RedisGeoCommands.GeoLocation<M>>> iterator = geoResults.iterator();
        List<RedisGeoCommands.GeoLocation<M>> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next().getContent());
        }
        return result;
    }
}
