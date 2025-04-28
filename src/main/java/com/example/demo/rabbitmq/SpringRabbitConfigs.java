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

    /**
    * @Author: yzd
    * @Date: 2024/1/13
     * 创建交换机
    */
    @Bean("redPacketExchange")
    public CustomExchange redPacketExchange(){
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-delayed-type", "direct");
        return new CustomExchange("redPacketExchange","x-delayed-message",true,false,arguments);
    }

    @Bean("redPacketQueue")
    public Queue redPacketQueue(){
        return QueueBuilder.durable("redPacketQueue").build();
    }

    @Bean
    public Binding redPacketBinding(){
        return BindingBuilder.bind(redPacketQueue()).to(redPacketExchange()).with("red.packet.key").noargs();
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
