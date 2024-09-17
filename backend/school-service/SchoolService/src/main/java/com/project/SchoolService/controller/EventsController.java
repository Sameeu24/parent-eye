package com.project.SchoolService.controller;

import com.project.SchoolService.domain.Events;
import com.project.SchoolService.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventsController {
    @Autowired
    private final EventsService eventsService;
    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }
    
    @GetMapping
    public ResponseEntity<List<Events>> getAllEvents(){
        List<Events> events = eventsService.getAllEvents();
        return ResponseEntity.ok(events);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Events> getEventsById(@PathVariable Long id){
        Events events = eventsService.getEventsById(id);
        return ResponseEntity.ok(events);
    }
}
