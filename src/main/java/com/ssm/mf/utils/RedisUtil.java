package com.ssm.mf.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisUtil
 * @Description TODO
 * @Author cfl
 * @Date 2019/6/21 10:54
 * @Version 1.0
 */
public class RedisUtil {
	private static final Logger log = LogManager.getLogger(RedisUtil.class);
	private RedisTemplate<Serializable, Object> redisTemplate;
	
	/**
	 * 批量删除对应的value
	 * @Param [keys]
	 * @Return void
	 */
	public void remove(final String... keys){
		for(String key : keys){
			log.info("batches deletes value by key : {}" + key);
			remove(key);
		}
	}

	/**
	 * 批量删除key
	 * @Param [pattern]
	 * @Return void
	 */
	public void removePattern(final String pattern){
		Set<Serializable> keys = redisTemplate.keys(pattern);
		if(keys.size() > 0){
			log.info("batches deletes key : {}" + keys);
			redisTemplate.delete(keys);
		}
	}
	
	/**
	 * 删除对应的value
	 * @Param [key]
	 * @Return void
	 */
	public void remove(final String key){
		if(exists(key)){
			log.info("delete value by key : {}" + key);
			redisTemplate.delete(key);
		}
	}
	
	/**
	 * 判断缓存中是否有对应的value
	 * @Param [key]
	 * @Return boolean
	 */
	public boolean exists(final String key){
		log.info("exists value : {}" + redisTemplate.hasKey(key));
		return redisTemplate.hasKey(key);
	}
	
	/**
	 * 读取缓存
	 * @Param [key]
	 * @Return java.lang.Object
	 */
	public Object get(final String key){
		Object result = null;
		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
		result = operations.get(key);
		log.info("get value by key : {}" + result);
		return result;
	}
	
	/**
	 * 写入缓存
	 * @Param [key, value]
	 * @Return boolean
	 */
	public boolean set(final String key, Object value){
		boolean result = false;
		try{
			log.info("set key : " + key + ", value : " + value);
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			result = true;
		} catch(Exception e){
			log.error(e.getMessage(), e);
		}
		return result;
	}
	
	public boolean set(final String key, Object value, Long expireTime){
		boolean result = false;
		try{
			log.info("set key : {}"+ key +", value : " + value + ", expireTime : " + expireTime);
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch(Exception e){
			log.error(e.getMessage(), e);
		}
		return result;
	}
	
	public void setRedisTemplate(RedisTemplate<Serializable, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
}
