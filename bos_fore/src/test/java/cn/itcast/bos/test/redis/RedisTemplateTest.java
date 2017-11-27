package cn.itcast.bos.test.redis;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class RedisTemplateTest {

	@Resource
	private RedisTemplate redisTemplate;
	
	@Test
	public void testRedisTemplate(){
		redisTemplate.opsForValue().set("test7", "value7", 10, TimeUnit.SECONDS);
		System.out.println(redisTemplate.opsForValue().get("test6"));
	}
}
