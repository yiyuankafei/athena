package com.application.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class TicketConsumer {
 
    @RabbitListener(queues = {"queue.ticket.unpay"}, containerFactory = "rabbitListenerContainerFactory")
    public void handleMessage(String message) throws Exception {
        // 处理消息
        System.out.println("正常消费消息" + message);
    }
    
    @RabbitListener(queues = {"queue.ticket.dlx.unpay"}, containerFactory = "rabbitListenerContainerFactory")
    public void handleDlxMessage(String message) throws Exception {
        // 处理消息
        System.out.println("延时消费消息:" + message);
    }
}
