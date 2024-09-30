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
    private int cumulativeTotalDuration = 0;  // Class-level variable to accumulate total duration

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
        Pattern pattern = Pattern.compile("Link: (.*), Status: (.*), Duration: (\\d+) minute\\(s\\)");
        Matcher matcher = pattern.matcher(message);

        if (matcher.find()) {
            String url = matcher.group(1);
            String status = matcher.group(2);
            int duration = Integer.parseInt(matcher.group(3));

            // Randomly modify the status (Allowed/Blocked)
            String updatedStatus = random.nextBoolean() ? "Allowed" : "Blocked";

            if ("Allowed".equals(updatedStatus)) {
                // Accumulate the duration to the total duration if the status is 'Allowed'
                cumulativeTotalDuration += duration;
            }

            // Update the message with the new status
            String updatedMessage = String.format("Link: %s, Status: %s, Duration: %d minute(s)",
                    url, updatedStatus, duration);

            // Log the updated message
            System.out.println("Updated message: " + updatedMessage);

            // Log the cumulative total duration
            System.out.println("Cumulative Total Duration (Allowed links only): " + cumulativeTotalDuration + " minute(s)");

            // Create and save the ProcessedMessage object with the cumulativeTotalDuration
            ProcessedMessage processedMessage = new ProcessedMessage(url, updatedStatus, duration, message, updatedMessage, cumulativeTotalDuration);
            processedMessageRepository.save(processedMessage);

            // Send the updated message to the outbound queue
            rabbitTemplate.convertAndSend(outboundQueue, updatedMessage);
        } else {
            System.err.println("Failed to parse the message format: " + message);
        }
    }
}
