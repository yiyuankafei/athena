package com.application.config;

import java.util.HashMap;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {
	
	@Bean
    public Queue ticketUnpayQueue() {
        /**
         durable="true" 持久化 rabbitmq重启的时候不需要创建新的队列
         auto-delete 表示消息队列没有在使用时将被自动删除 默认是false
         exclusive  表示该消息队列是否只在当前connection生效,默认是false
         */
		HashMap<String,Object> args = new HashMap<>();
		args.put("x-message-ttl", 10000);  
		args.put("x-dead-letter-exchange", RabbitMqConfig.EXCHANGE_TICKET_DLX);
		args.put("x-dead-letter-routing-key", RabbitMqConfig.ROUTINGKEY_TICKET_DLX_UNPAY);
        return new Queue(RabbitMqConfig.QUEUE_TICKET_UNPAY,true,false,false,args);
    }
	
	@Bean
    public Queue ticketUncheckQueue() {
		HashMap<String,Object> args = new HashMap<>();
		args.put("x-message-ttl", 30000);  
		args.put("x-dead-letter-exchange", RabbitMqConfig.EXCHANGE_TICKET_DLX);
		args.put("x-dead-letter-routing-key", RabbitMqConfig.ROUTINGKEY_TICKET_DLX_UNCHECK);
        return new Queue(RabbitMqConfig.QUEUE_TICKET_UNCHECK,true,false,false,args);
    }
	
    @Bean
    public Queue dlxUnpayQueue() {
        return new Queue(RabbitMqConfig.QUEUE_TICKET_DLX_UNPAY,true,false,false);
    }
    
    @Bean
    public Queue dlxUncheckQueue() {
        return new Queue(RabbitMqConfig.QUEUE_TICKET_DLX_UNCHECK,true,false,false);
    }
    
    @Bean
    public Queue fanoutQueue1() {
        return new Queue(RabbitMqConfig.QUEUE_FANOUT1,true,false,false);
    }
    
    @Bean
    public Queue fanoutQueue2() {
        return new Queue(RabbitMqConfig.QUEUE_FANOUT2,true,false,false);
    }
    
    @Bean
    public Queue fanoutQueue3() {
        return new Queue(RabbitMqConfig.QUEUE_FANOUT3,true,false,false);
    }

}
