package com.example.system.utils;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@Component
public class RedisUtil {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /* @Autowired
     private JedisCluster  jedisCluster;*/
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 设置缓存
     *
     * @param key   缓存key
     * @param value 缓存value
     */
    public void set(String key, String value) {
        System.out.println(value);
        try {
            //jedisCluster.set(key, value);
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.debug("RedisUtil:set cache key={},value={}", key, value);
    }

    /**
     * 设置缓存对象
     *
     * @param key 缓存key
     * @param obj 缓存value
     */
    public <T> void setObject(String key, T obj, int expireTime) {
        //jedisCluster.setex(key, expireTime, JSON.toJSONString(obj));
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        System.out.println(JSON.toJSONString(obj));
        operations.set(key, JSON.toJSONString(obj), expireTime, TimeUnit.SECONDS);
    }

    /**
     * 获取指定key的缓存
     *
     * @param key---JSON.parseObject(value, User.class);
     */
    public String getObject(String key) {
        //return jedisCluster.get(key);
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        return operations.get(key).toString();
    }

    /**
     * 判断当前key值 是否存在
     *
     * @param key
     */
    public boolean hasKey(String key) {
        //return jedisCluster.exists(key);
        return redisTemplate.hasKey(key);
    }


    /**
     * 设置缓存，并且自己指定过期时间
     *
     * @param key
     * @param value
     * @param expireTime 过期时间
     */
    public void setWithExpireTime(String key, String value, int expireTime) {
        //jedisCluster.setex(key, expireTime, value);
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        operations.set(key, value, expireTime, TimeUnit.SECONDS);
        LOGGER.debug("RedisUtil:setWithExpireTime cache key={},value={},expireTime={}", key, value, expireTime);
    }


    /**
     * 获取指定key的缓存
     *
     * @param key
     */
    public String get(String key) {
        //String value = jedisCluster.get(key);
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        Object value = operations.get(key);
        if (value == null) {
            return null;
        }
        LOGGER.debug("RedisUtil:get cache key={},value={}", key, value);
        return value.toString();
    }

    /**
     * 删除指定key的缓存
     *
     * @param key
     */
    public void delete(String key) {
        //jedisCluster.del(key);
        redisTemplate.delete(key);

        LOGGER.debug("RedisUtil:delete cache key={}", key);
    }

    /**
     * 删除指定key的缓存
     *
     * @param key
     */
    public void deleteLike(String key) {
        Set<String> keys = redisTemplate.keys("*" + key);
        redisTemplate.delete(keys);
        LOGGER.debug("RedisUtil:delete cache key={}", key);
    }

    /**
     * 设置过期时间
     *
     * @param key
     * @param seconds
     * @author yfw
     */
    public void expire(String key, int seconds) {
        if (seconds <= 0) {
            return;
        }
        //jedisCluster.expire(key, seconds);  
        redisTemplate.expire(key, seconds, TimeUnit.SECONDS);

    }
}









