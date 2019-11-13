package com.application.provider;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.application.config.RabbitMqConfig;

@Component
public class TicketSender {
 
    @Autowired
    private RabbitTemplate rabbitTemplate;
 
    /**
     * 发送消息
     * @param uuid
     * @param message  消息
     */
    public void sendTicketUnpay(String uuid,Object message) {
        CorrelationData correlationId = new CorrelationData(uuid);
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_TICKET, RabbitMqConfig.ROUTINGKEY_TICKET_UNPAY,
                message, correlationId);
    }
    
    /**
     * 发送消息
     * @param uuid
     * @param message  消息
     */
    public void sendTicketUncheck(String uuid,Object message) {
        CorrelationData correlationId = new CorrelationData(uuid);
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_TICKET, RabbitMqConfig.ROUTINGKEY_TICKET_UNCHECK,
                message, correlationId);
    }
    
    /**
     * 发送消息
     * @param uuid
     * @param message  消息
     */
    public void sendFanout(String uuid,Object message) {
        CorrelationData correlationId = new CorrelationData(uuid);
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_FANOUT, null,
                message, correlationId);
    }
    
    /**
     * 发送消息
     * @param uuid
     * @param message  消息
     */
    public void sendNo(String uuid,Object message) {
        CorrelationData correlationId = new CorrelationData(uuid);
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_TICKET, "4567",
                message, correlationId);
    }
    
    
    
}
