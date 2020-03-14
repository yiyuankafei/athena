package com.application.consumer.origin;

import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

public class Consumer {
	
	/**
	 * 
	 * 主要流程：
	 * 1. 创建一个DefaultMQPushConsumer对象，设置好groupName和NameServer地址后
	 * 2. 设置订阅的topic,订阅位置
	 * 3. 注册Listener,处理业务逻辑
	 * 4. 启动consumer
	 */
	
	public static void main(String[] args) throws Exception {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("conusmer_group_name");
		consumer.setNamesrvAddr("127.0.0.1:9876");
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
		consumer.subscribe("topicTest", "*");
		consumer.registerMessageListener(new MessageListenerConcurrently(){
			
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				System.out.println(Thread.currentThread().getName() + "Receive new message:" + msgs);
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
		consumer.start();
	}

}
