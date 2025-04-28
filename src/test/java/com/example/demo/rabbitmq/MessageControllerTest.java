package com.example.demo.rabbitmq;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MessageControllerTest {

    @Mock
    private AmqpTemplate amqpTemplate;


    @Test
    public void testSendMessage() {
        MessageController messageController = new MessageController(amqpTemplate);
        // Arrange
        String expectedMessage = "Message Sent";

        // Act
        String actualMessage = messageController.sendMessage();

        // Assert
        verify(amqpTemplate, times(1)).convertAndSend("queue-demo", "Sample message using amqp template");
        assertEquals(expectedMessage, actualMessage);
    }
}