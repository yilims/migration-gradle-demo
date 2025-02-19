package com.example.demo.rabbitmq;

import com.azure.spring.messaging.servicebus.core.ServiceBusTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.Message;

/**
 * Spring controller exposes api for Home controller.
 */
@RestController
public class MessageController {

	private final ServiceBusTemplate serviceBusTemplate;

	@Autowired
	public MessageController(ServiceBusTemplate serviceBusTemplate) {
		this.serviceBusTemplate = serviceBusTemplate;
	}

	@GetMapping("/sendMessage")
	public String sendMessage() {
		Message<String> message = MessageBuilder.withPayload("Sample message using service bus template").build();
		serviceBusTemplate.send("queue-demo", message);
		return "Message Sent";
	}

}