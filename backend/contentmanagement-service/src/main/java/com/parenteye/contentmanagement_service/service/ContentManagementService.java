package com.parenteye.contentmanagement_service.service;
import com.parenteye.contentmanagement_service.repository.ProcessedMessageRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.parenteye.contentmanagement_service.domian.ProcessedMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Random;

@Service
public class ContentManagementService {

    private final RabbitTemplate rabbitTemplate;
    private final Random random = new Random();
    private final ProcessedMessageRepository processedMessageRepository;

    @Value("${rabbitmq.queue.outbound}")
    private String outboundQueue;

    public ContentManagementService(RabbitTemplate rabbitTemplate, ProcessedMessageRepository processedMessageRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.processedMessageRepository = processedMessageRepository;
    }

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);

        // Extract the necessary parts from the message (URL, Status, Duration)
        Pattern pattern = Pattern.compile("Link: (.*), Status: (.*), Duration: (\\d+) ms");
        Matcher matcher = pattern.matcher(message);

        if (matcher.find()) {
            String url = matcher.group(1);
            String status = matcher.group(2);
            int duration = Integer.parseInt(matcher.group(3));

            // Randomly modify the status (Allowed/Blocked)
            String updatedStatus = random.nextBoolean() ? "Allowed" : "Blocked";
            String updatedMessage = message.replaceFirst("Status: [A-Za-z]+", "Status: " + updatedStatus);

            System.out.println("Updated message: " + updatedMessage);

            // Save the message to MongoDB
            ProcessedMessage processedMessage = new ProcessedMessage(url, updatedStatus, duration, message, updatedMessage);
            processedMessageRepository.save(processedMessage);

            // Send the updated message to the outbound queue
            rabbitTemplate.convertAndSend(outboundQueue, updatedMessage);
        } else {
            System.err.println("Failed to parse the message format: " + message);
        }
    }
}
