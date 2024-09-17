package com.project.SchoolService.controller;

import com.project.SchoolService.domain.Events;
import com.project.SchoolService.service.EventsService;
import feign.Response;
import jdk.jfr.Event;
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
    @PostMapping
    public ResponseEntity<Events> createEvent(@RequestBody Events events){
        Events createdEvent = eventsService.createEvent(events);
        return ResponseEntity.ok(createdEvent);

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
    @GetMapping
    public ResponseEntity<Events> updateEvent(@PathVariable Long id, @RequestBody Events events){
        Events updateEvent = eventsService.updateEvents(id,events);
        return ResponseEntity.ok(updateEvent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventsById(@PathVariable Long id){
        eventsService.deleteEvents(id);
        return ResponseEntity.noContent().build();
    }
}
