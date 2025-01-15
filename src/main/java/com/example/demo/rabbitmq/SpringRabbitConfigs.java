package com.example.demo.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringRabbitConfigs {

	private static final String EXCHANGE_NAME = "fanout.ex";

	private static final String QUEUE_NAME = "queue.ex";

	@Bean
	public Queue createQueue() {
		// For learning purpose - durable=false,
		// in a real project you may need to set this as true.
		return new Queue(QUEUE_NAME, true);
	}

	@Bean
	public Exchange fanoutExchange() {
		// durable=true, autoDelete=false
		return new FanoutExchange(EXCHANGE_NAME, true, false);
	}

	@Bean
	public Binding queueBinding() {
		return new Binding(QUEUE_NAME, Binding.DestinationType.QUEUE, EXCHANGE_NAME, "", null);
	}

	public void convertAndSend(){
		// This is a dummy method to demonstrate the usage of AmqpTemplate.
		// In a real project, you may need to inject AmqpTemplate and use it.
		// amqpTemplate.convertAndSend("queue-test", "Sample message using amqp template");
	}
}
