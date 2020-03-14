package com.application.producer.origin;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class SyncProducer {
	
	/**
	 * 
	 * 主要流程：
	 * 1. 创建一个DefaultMQProducer对象，设置好groupName和NameServer地址
	 * 2. 启动producer
	 * 3. 把待发送的消息封装成Message对象
	 * 4. 使用producer发送
	 */
	
	public static void main(String[] args) throws Exception {
		DefaultMQProducer producer = new DefaultMQProducer("producer_group_name");
		producer.setNamesrvAddr("127.0.0.1:9876");
		producer.start();
		for (int i = 0; i < 100; i++) {
			Message msg = new Message("TopicTest","Tag-A","helloRocketMq".getBytes(RemotingHelper.DEFAULT_CHARSET));
			SendResult sendResult = producer.send(msg);
			System.out.printf("%s%n", sendResult);
		}
		producer.shutdown();
	}

}
