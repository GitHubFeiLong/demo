package ssm.redis.work1;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 *
 * 性能对比
 * @ClassName SexCompareTest
 * @Description TODO
 * @Author cfl
 * @Date 2019/6/27 11:06
 * @Version 1.0
 */
public class SexCompareTest {
	public static void dels(String...keys){
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		jedis.auth("123456");
		for (int i = 0; i < keys.length; i++) {
			jedis.del(keys[i]);
		}
	}
	
	public static void delsByPipe(String...keys){
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		jedis.auth("123456");
		Pipeline pipeline = jedis.pipelined();
		for (int i = 0; i < keys.length; i++) {
//			jedis.del(keys[i]);
			pipeline.del(keys[i]);
		}
		pipeline.sync();    // 打包发送redis
	}
	public static void main(String[] args) {
		// 初始化数据，放到数组中
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		jedis.auth("123456");
		String [] arr = new String[5000];
		long start = System.currentTimeMillis();
		for(int i = 0; i < arr.length; i++){
			arr[i] = ""+i;
			jedis.set(""+i, ""+i);
		}
		long end = System.currentTimeMillis();
		System.out.println("插入完毕，用时：" + (long)(end-start));
		
		start = System.currentTimeMillis();
		dels(arr);
		end = System.currentTimeMillis();
		System.out.println("删除完成，用时：" + (end-start));
	}
}
