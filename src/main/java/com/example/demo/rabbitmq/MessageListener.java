package com.example.demo.rabbitmq;

import com.azure.spring.messaging.implementation.annotation.EnableAzureMessaging;
import com.azure.spring.messaging.servicebus.implementation.core.annotation.ServiceBusListener;
import com.azure.spring.messaging.servicebus.core.ServiceBusTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@EnableAzureMessaging
public class MessageListener {

	private final ServiceBusTemplate serviceBusTemplate;

	@Autowired
	public MessageListener(ServiceBusTemplate serviceBusTemplate) {
		this.serviceBusTemplate = serviceBusTemplate;
	}

	/**
	 * Assigns a Consumer to receive the messages whenever there is one.
	 * @param message
	 */
	@ServiceBusListener(destination = "queue.excur")
	public void receiveMessage(String message) {
		System.out.println("Received Message:" + message);
		serviceBusTemplate.send("queue-test", org.springframework.messaging.support.MessageBuilder.withPayload("Sample message using service bus template").build());
	}

}