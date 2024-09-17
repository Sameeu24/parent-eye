package com.project.screentime_service.service;


import com.project.screentime_service.domain.Link;
import com.project.screentime_service.repository.LinkRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class    LinkService {


    private final LinkRepository linkRepository;

    private final Random random = new Random();

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @Scheduled(fixedRateString = "${scheduler.fixedRate:5000}") // Default to 5 seconds if not specified
    public void logRandomLinkAndDuration() {
        List<Link> links = linkRepository.findAll();
        if (links.isEmpty()) {
            System.out.println("No links found in the database.");
            return;
        }

        // Select a random link
        Link randomLink = links.get(random.nextInt(links.size()));

        // Select a random duration (for example, between 1 and 10 seconds)
        int randomDuration = 1000 * (random.nextInt(10) + 1);

        // Log the results
        System.out.printf("Selected link: %s, Duration: %d ms%n", randomLink.getUrl(), randomDuration);
    }
}