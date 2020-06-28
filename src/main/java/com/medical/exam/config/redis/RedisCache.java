package com.medical.exam.config.redis;

import com.medical.exam.util.SpringUtil;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Wu
 * redis二级缓存实现
 */
public class RedisCache implements Cache {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisCache.class);
    /**
     * 读写锁
     */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    /**
     * 缓存实例id
     */
    private final String id;
    private RedisTemplate redisTemplate;

    /**
     * 缓存过期时间,目前为6个小时
     */
    private static final long EXPIRE_TIME_IN_MINUTES = 60*6;

    public RedisCache(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        LOGGER.info("Redis Cache id " + id);
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    /**
     * Put query result to redis
     *
     * @param key
     * @param value
     */
    @Override
    public void putObject(Object key, Object value) {
        try {
            RedisTemplate redisTemplate = getRedisTemplate();
            ValueOperations opsForValue = redisTemplate.opsForValue();
            opsForValue.set(key.toString(), value, EXPIRE_TIME_IN_MINUTES, TimeUnit.MINUTES);
            LOGGER.debug("Put query result to redis");
        }
        catch (Throwable t) {
            LOGGER.error("Redis put failed", t);
        }
    }

    /**
     * Get cached query result from redis
     *
     * @param key
     * @return
     */
    @Override
    public Object getObject(Object key) {
        try {

            RedisTemplate redisTemplate = getRedisTemplate();
            ValueOperations opsForValue = redisTemplate.opsForValue();
            Object o=opsForValue.get(key.toString());
            if(o==null){
                LOGGER.debug("Redis get failed, fail over to db");
            }else{
                LOGGER.debug("Get cached query result from redis,key:"+key+" value:"+o.toString());
            }
            return o;
        }
        catch (Throwable t) {
            LOGGER.error("Redis get failed, fail over to db", t);
            return null;
        }
    }

    /**
     * Remove cached query result from redis
     *
     * @param key
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object removeObject(Object key) {
        try {
            RedisTemplate redisTemplate = getRedisTemplate();
            redisTemplate.delete(key.toString());
            LOGGER.debug("Remove cached query result from redis");
        }
        catch (Throwable t) {
            LOGGER.error("Redis remove failed", t);
        }
        return null;
    }

    /**
     * Clears this cache instance
     */
    @Override
    public void clear() {
        try {
            Set<String> keys = redisTemplate.keys("*:" + this.id + "*");
            if (!CollectionUtils.isEmpty(keys)) {
                redisTemplate.delete(keys);
            }
        } catch (Exception e) {

        }
        LOGGER.debug("Clear all the cached query result from redis");
    }

    /**
     * This method is not used
     *
     * @return
     */
    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

    private RedisTemplate getRedisTemplate() {
        if (redisTemplate == null) {
            redisTemplate = (RedisTemplate) SpringUtil.getBean("redisTemplate");
        }
        return redisTemplate;
    }

}