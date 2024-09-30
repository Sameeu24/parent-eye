package com.parenteye.contentmanagement_service.controller;

import com.parenteye.contentmanagement_service.domian.ProcessedMessage;
import com.parenteye.contentmanagement_service.repository.ProcessedMessageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/content")
public class ProcessedMessageController {

    private final ProcessedMessageRepository processedMessageRepository;

    public ProcessedMessageController(ProcessedMessageRepository processedMessageRepository) {
        this.processedMessageRepository = processedMessageRepository;
    }

    // Endpoint to get all processed messages
    @GetMapping
    public ResponseEntity<List<ProcessedMessage>> getAllMessages() {
        List<ProcessedMessage> messages = processedMessageRepository.findAll();
        return ResponseEntity.ok(messages);
    }

    // Endpoint to get a single message by ID

}
