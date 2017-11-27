package cn.itcast.bos.test.activemq;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.activemq.producer.queue.QueueSender;
import cn.itcast.activemq.producer.topic.TopicSender;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ActiveMQSpringProducerTest {

	@Resource
	private QueueSender queueSender;
	@Resource
	private TopicSender topicSender;
	
	@Test
	public void testProducer(){
		//1.发送字符串队列消息
		queueSender.send("test.queue", "这是一个队列字符串消息！");
		Map<String, String> map = new HashMap<String,String>();
		map.put("company", "传智播客");
		//2.发送map队列消息
		queueSender.send("test.map", map);
		//3.发送字符串话题消息
		topicSender.send("test.topic", "这是一个话题字符串消息！");
	}
}
