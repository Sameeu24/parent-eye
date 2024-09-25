package com.parenteye.contentmanagement_service.config;
import org.springframework.amqp.core.Queue;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ContentManagementConfig {

    @Bean
    public Queue inboundQueue() {
        return new Queue("screen.queue", false);
    }

    @Bean
    public Queue outboundQueue() {
        return new Queue("screen.queue.updated", false);
    }




}
