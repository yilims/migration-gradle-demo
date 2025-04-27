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
		return QueueBuilder.durable(queue).build();
	}

	@Bean
	public DirectExchange exchange(){
		return new DirectExchange(exchange);
	}

	@Bean
	public Binding queueBinding(){
		return BindingBuilder.bind(this.createQueue).to(this.exchange).with("test.key").noargs();
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
