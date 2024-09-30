package com.project.screentime_service.service;

import com.project.screentime_service.domain.Link;
import com.project.screentime_service.domain.LinkStatus;
import com.project.screentime_service.repository.LinkRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class LinkService {

    private final LinkRepository linkRepository;
    private final Random random = new Random();
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    public LinkService(LinkRepository linkRepository, RabbitTemplate rabbitTemplate) {
        this.linkRepository = linkRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedRateString = "${scheduler.fixedRate:5000}") // Default to 5 seconds if not specified
    public void logRandomLinkAndDuration() {
        // Fetch all links
        List<Link> links = linkRepository.findAll();
        if (links.isEmpty()) {
            System.out.println("No links found in the database.");
            return;
        }

        // Select a random link
        Link randomLink = links.get(random.nextInt(links.size()));

        // Set a random Status (either ALLOWED or BLOCKED)
        LinkStatus randomStatus = getRandomStatus();
        randomLink.setLinkStatus(randomStatus);

        // Select a random duration (for example, between 1 and 10 minutes)
        int randomDurationInMinutes = random.nextInt(10) + 1;

        // Convert the duration to milliseconds (for scheduling purposes, if needed)
        int randomDurationInMillis = randomDurationInMinutes * 60 * 1000;

        String message = String.format("Link: %s, Status: %s, Duration: %d minute(s)", randomLink.getUrl(), randomLink.getLinkStatus(), randomDurationInMinutes);
        System.out.println("Sending message: " + message);

        rabbitTemplate.convertAndSend(queueName, message);

        // Log the results
        System.out.printf("Selected link: %s, Status: %s, Duration: %d minute(s)%n", randomLink.getUrl(), randomLink.getLinkStatus(), randomDurationInMinutes);

        // Optional: Save the updated status to the database
        linkRepository.save(randomLink);
    }

    @RabbitListener(queues = "screen.queue.updated")
    public void receiveUpdatedMessage(String message) {
        System.out.println("Received updated message: " + message);
    }

    // Helper method to get a random status
    private LinkStatus getRandomStatus() {
        return random.nextBoolean() ? LinkStatus.ALLOWED : LinkStatus.BLOCKED;
    }
}
