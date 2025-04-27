package com.example.demo.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.rabbitmq.jms.admin.RMQConnectionFactory;
import jakarta.jms.ConnectionFactory;

@Configuration
public class SpringRabbitConfigs {
    @Value("${rabbitmq.queue.name}")
    private String queue;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

	@Bean
	public Queue createQueue() {
		// For learning purpose - durable=false,
		// in a real project you may need to set this as true.
		return new Queue(queue, true);
	}

	@Bean
	public Exchange fanoutExchange() {
		// durable=true, autoDelete=false
		return new FanoutExchange(exchange, true, false);
	}

	@Bean
	public Binding queueBinding() {
		return new Binding(queue, Binding.DestinationType.QUEUE, EXCHANGE_NAME, "", null);
	}

    @Bean
    public ConnectionFactory connectionFactory(){
        RMQConnectionFactory connectionFactory = new RMQConnectionFactory();
        connectionFactory.setUsername(rabbitProps.getUsername());
        connectionFactory.setPassword(rabbitProps.getPassword());
        connectionFactory.setHost(rabbitProps.getHost());
        connectionFactory.setPort(5672);
        return connectionFactory;
    }
}
