package com.application.config;


import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfig {
	
	/**
     * 门票死信交换机
     */
    public static final String EXCHANGE_TICKET_DLX = "exchange.ticket.dlx";
    
    /**
     * 绑定建-门票死信-未支付
     */
    public static final String ROUTINGKEY_TICKET_DLX_UNPAY = "key.ticket.dlx.unpay";
	
	/**
     * 门票待支付队列
     */
    public static final String QUEUE_TICKET_UNPAY = "queue.ticket.unpay";
    
    
    /**
     * 门票死信队列-未支付
     */
    public static final String QUEUE_TICKET_DLX_UNPAY = "queue.ticket.dlx.unpay";
	
    @Autowired
    private QueueConfig queueConfig;
    /**
     * 连接工厂
     */
    @Autowired
    private ConnectionFactory connectionFactory;
    
    
    /**
     * queue listener  观察 监听模式
     * 当有消息到达时会通知监听在对应的队列上的监听对象
     * @return
     */
    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer_one(){
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
        simpleMessageListenerContainer.addQueues(queueConfig.unpayQueue());
        simpleMessageListenerContainer.addQueues(queueConfig.dlxUncheckQueue());
        simpleMessageListenerContainer.setExposeListenerChannel(true);
        simpleMessageListenerContainer.setMaxConcurrentConsumers(5);
        simpleMessageListenerContainer.setConcurrentConsumers(1);
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        return simpleMessageListenerContainer;
        
    }

}