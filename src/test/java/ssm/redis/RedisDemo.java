package ssm.redis;

import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;

/**
 * @ClassName RedisDemo
 * @Description TODO
 * @Author cfl
 * @Date 2019/6/21 17:04
 * @Version 1.0
 */
public class RedisDemo {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		//权限认证
		//jedis.auth("123456");
		jedis.set("lisonLength", "3");
		jedis.close();
	}
}
