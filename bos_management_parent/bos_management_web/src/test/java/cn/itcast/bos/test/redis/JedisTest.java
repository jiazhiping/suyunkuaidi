package cn.itcast.bos.test.redis;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class JedisTest {

	@Test
	public void testJedis(){
		Jedis jedis = new Jedis("localhost");
//		jedis.set("test6", "value6");
//		String value = jedis.get("test6");
//		System.out.println(value);
		
		jedis.setex("test7", 10, "value7");
	}
}
