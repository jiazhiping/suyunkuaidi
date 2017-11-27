
package cn.itcast.activemq.consumer.queue;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.stereotype.Component;

import cn.itcast.bos.utils.SmsUtils;

/**
 * 
 * @description 队列消息监听器
 * 
 */
@Component
public class QueueReceiver3 implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			MapMessage map = ((MapMessage) message);
			String telephone = map.getString("telephone");
			String msg = map.getString("msg");
			//SmsUtils.sendSmsByWebService(telephone, msg);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
