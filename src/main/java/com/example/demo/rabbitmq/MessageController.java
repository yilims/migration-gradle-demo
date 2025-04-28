package com.example.demo.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring controller exposes api for Home controller.
 */
@RestController
public class MessageController {
	
	@Value("${rabbitmq.queue.name}")
	private String queueName;

	private final AmqpTemplate amqpTemplate;

	@Autowired
	public MessageController(AmqpTemplate amqpTemplate) {
		this.amqpTemplate = amqpTemplate;
	}

	@GetMapping("/sendMessage")
	public String sendMessage() {
		amqpTemplate.convertAndSend(queueName, "Sample message using amqp template");
		return "Message Sent";
	}

}
