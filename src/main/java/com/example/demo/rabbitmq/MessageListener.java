package com.example.demo.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

	private final AmqpTemplate amqpTemplate;

	@Autowired
	public MessageListener(AmqpTemplate amqpTemplate) {
		this.amqpTemplate = amqpTemplate;
	}

	/**
	 * Assigns a Consumer to receive the messages whenever there is one.
	 * @param message
	 */
	@RabbitListener(queues = {"redPacketQueue"})
	public void receiveMessage(String message) {
		System.out.println("Received Message:" + message);
		amqpTemplate.convertAndSend("queue-test", "Sample message using amqp template");
	}

}
