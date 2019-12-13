package com.application.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

import javax.annotation.Resource;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.athena.common.response.ResEnv;

@RestController
public class TicketController {
	
	
	@Resource
    private RocketMQTemplate rocketMQTemplate;
	
	/**
     * topic,消息依赖于topic
     */
    private static final String topic = "topic-byf-test";
    							
	
	@RequestMapping("/test/producer")
	public ResEnv<?> testProducer() throws Exception {
		
		String path = "C:\\Users\\Administrator\\Desktop\\test.txt";
		
		String string = null;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "GBK"));
			string = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Message<String> message = MessageBuilder.withPayload(string).build();
		StringBuilder destination = new StringBuilder(topic);
		SendResult sendResult = rocketMQTemplate.syncSend(destination.toString(), message);
		if (SendStatus.SEND_OK == sendResult.getSendStatus()) {
			return ResEnv.success();
		} else {
			return ResEnv.fail("");
		}

	}
	
	class OrderPaidEvent implements Serializable {

	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private String orderId;

	    private Integer paidMoney;

		public String getOrderId() {
			return orderId;
		}

		public void setOrderId(String orderId) {
			this.orderId = orderId;
		}

		public Integer getPaidMoney() {
			return paidMoney;
		}

		public void setPaidMoney(Integer paidMoney) {
			this.paidMoney = paidMoney;
		}

		public OrderPaidEvent(String orderId, Integer paidMoney) {
			super();
			this.orderId = orderId;
			this.paidMoney = paidMoney;
		}
	}
	
}
