package com.application.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.application.callback.MsgSendConfirmCallBack;


@Configuration
public class RabbitMqConfig {
	
	
	/**
	 * 门票交换机
	 */
    public static final String EXCHANGE_TICKET = "exchange.ticket";
    
    
    /**
     * 门票死信交换机
     */
    public static final String EXCHANGE_TICKET_DLX = "exchange.ticket.dlx";
    
    /**
     * fanout交换机
     */
    public static final String EXCHANGE_FANOUT = "exchange.fanout";
    
    
    /**
     * 门票待支付队列
     */
    public static final String QUEUE_TICKET_UNPAY = "queue.ticket.unpay";
    
    /**
     * 门票待核销队列
     */
    public static final String QUEUE_TICKET_UNCHECK = "queue.ticket.uncheck";
    
    
    /**
     * 门票死信队列-未支付
     */
    public static final String QUEUE_TICKET_DLX_UNPAY = "queue.ticket.dlx.unpay";
    
    /**
     * 门票死信队列-未核销
     */
    public static final String QUEUE_TICKET_DLX_UNCHECK = "queue.ticket.dlx.uncheck";
    
    /**
     * fanout队列1
     */
    public static final String QUEUE_FANOUT1 = "queue.fanout1";
    
    /**
     * fanout队列2
     */
    public static final String QUEUE_FANOUT2 = "queue.fanout2";
    
    /**
     * fanout队列3
     */
    public static final String QUEUE_FANOUT3 = "queue.fanout3";
    
    
    /**
     * 绑定建-门票待支付
     */
    public static final String ROUTINGKEY_TICKET_UNPAY = "key.ticket.unpay";
    
    /**
     * 绑定建-门票待核销
     */
    public static final String ROUTINGKEY_TICKET_UNCHECK = "key.ticket.check";
    
    
    /**
     * 绑定建-门票死信-未支付
     */
    public static final String ROUTINGKEY_TICKET_DLX_UNPAY = "key.ticket.dlx.unpay";
    
    /**
     * 绑定建-门票死信-未核销
     */
    public static final String ROUTINGKEY_TICKET_DLX_UNCHECK = "key.ticket.dlx.uncheck";
    
    
    @Autowired
    private QueueConfig queueConfig;
    @Autowired
    private ExchangeConfig exchangeConfig;
    
    /**
     * 连接工厂
     */
    @Autowired
    private ConnectionFactory connectionFactory;
    
    /**
    * 将业务消息队列和业务交换机进行绑定
    */
    @Bean
    public Binding binding_ticket_unpay() {
        return BindingBuilder.bind(queueConfig.ticketUnpayQueue()).to(exchangeConfig.ticketExchange()).with(RabbitMqConfig.ROUTINGKEY_TICKET_UNPAY);
    }
    
    /**
     * 将业务消息队列和业务交换机进行绑定
     */
     @Bean
     public Binding binding_ticket_uncheck() {
         return BindingBuilder.bind(queueConfig.ticketUncheckQueue()).to(exchangeConfig.ticketExchange()).with(RabbitMqConfig.ROUTINGKEY_TICKET_UNCHECK);
     }
    
    /**
     * 将死信消息队列和死信交换机进行绑定
     */
    @Bean
    public Binding binding_ticket_dlx_unpay() {
        return BindingBuilder.bind(queueConfig.dlxUnpayQueue()).to(exchangeConfig.ticketDlxExchange()).with(RabbitMqConfig.ROUTINGKEY_TICKET_DLX_UNPAY);
    }
    
    @Bean
    public Binding binding_ticket_dlx_uncheck() {
        return BindingBuilder.bind(queueConfig.dlxUncheckQueue()).to(exchangeConfig.ticketDlxExchange()).with(RabbitMqConfig.ROUTINGKEY_TICKET_DLX_UNCHECK);
    }
    
    @Bean
    public Binding binding_fanout1() {
        return BindingBuilder.bind(queueConfig.fanoutQueue1()).to(exchangeConfig.fanoutExchange());
    }
    
    @Bean
    public Binding binding_fanout2() {
        return BindingBuilder.bind(queueConfig.fanoutQueue2()).to(exchangeConfig.fanoutExchange());
    }
    
    @Bean
    public Binding binding_fanout3() {
        return BindingBuilder.bind(queueConfig.fanoutQueue3()).to(exchangeConfig.fanoutExchange());
    }
    
    
	
    /**
     * 定义rabbit template用于数据的接收和发送
     * @return
     */
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        /**若使用confirm-callback或return-callback，
         * 必须要配置publisherConfirms或publisherReturns为true
         * 每个rabbitTemplate只能有一个confirm-callback和return-callback
         */
        template.setConfirmCallback(msgSendConfirmCallBack());
        //template.setReturnCallback(msgSendReturnCallback());
        /**
         * 使用return-callback时必须设置mandatory为true，或者在配置中设置mandatory-expression的值为true，
         * 可针对每次请求的消息去确定’mandatory’的boolean值，
         * 只能在提供’return -callback’时使用，与mandatory互斥
         */
        //  template.setMandatory(true);
        return template;
    }
    
    /**
     * 消息确认机制
     * Confirms给客户端一种轻量级的方式，能够跟踪哪些消息被broker处理，
     * 哪些可能因为broker宕掉或者网络失败的情况而重新发布。
     * 确认并且保证消息被送达，提供了两种方式：发布确认和事务。(两者不可同时使用)
     * 在channel为事务时，不可引入确认模式；同样channel为确认模式下，不可使用事务。
     * @return
     */
    @Bean
    public MsgSendConfirmCallBack msgSendConfirmCallBack(){
        return new MsgSendConfirmCallBack();
    }

}