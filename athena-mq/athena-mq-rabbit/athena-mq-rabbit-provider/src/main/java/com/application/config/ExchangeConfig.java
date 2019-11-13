package com.application.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangeConfig {
	
	/**
	 * 
	 * 门票交换机
	 */
	@Bean
	public DirectExchange ticketExchange() {
		return new DirectExchange(RabbitMqConfig.EXCHANGE_TICKET, true, false);
	}
	
	/**
	 * 
	 * 门票死信交换机
	 */
	@Bean
	public DirectExchange ticketDlxExchange() {
		return new DirectExchange(RabbitMqConfig.EXCHANGE_TICKET_DLX, true, false);
	}
	
	/**
	 * 
	 * fanout交换机
	 */
	@Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange(RabbitMqConfig.EXCHANGE_FANOUT, true, false);
	}

}
