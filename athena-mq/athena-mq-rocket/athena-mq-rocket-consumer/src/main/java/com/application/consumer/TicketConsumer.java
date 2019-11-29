package com.application.consumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = "topic-ticket-dev", consumerGroup = "ticket-consumer")
public class TicketConsumer implements RocketMQListener<String> {

	@Override
	public void onMessage(String message) {
		System.out.println("客户端消费消息：" + message);
	}

}
