package com.parenteye.contentmanagement_service.service;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ContentManagementService {


    private final RabbitTemplate rabbitTemplate;
    private final Random random = new Random();

    @Value("${rabbitmq.queue.outbound}")
    private String outboundQueue;


    public ContentManagementService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);

        // Randomly modify the status (Allowed/Blocked)
        String updatedStatus = random.nextBoolean() ? "Allowed" : "Blocked";
        String updatedMessage = message.replaceFirst("Status: [A-Za-z]+", "Status: " + updatedStatus);

        System.out.println("Updated message: " + updatedMessage);

        // Send the updated message to the outbound queue
        rabbitTemplate.convertAndSend(outboundQueue, updatedMessage);
    }
}
