package com.project.SchoolService.controller;

import com.project.SchoolService.domain.Assignment;
import com.project.SchoolService.domain.Events;
import com.project.SchoolService.service.AssignmentService;
import com.project.SchoolService.service.EventsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school")
public class SchoolController {

    private final AssignmentService assignmentService;
    private final EventsService eventsService;

    public SchoolController(AssignmentService assignmentService, EventsService eventsService) {
        this.assignmentService = assignmentService;
        this.eventsService = eventsService;
    }

    // Assignment Endpoints
    @PostMapping("/assignments")
    public ResponseEntity<Assignment> createAssignment(@RequestBody Assignment assignment) {
        Assignment createdAssignment = assignmentService.createAssignment(assignment);
        return ResponseEntity.ok(createdAssignment);
    }

    @GetMapping("/assignments")
    public ResponseEntity<List<Assignment>> getAllAssignments() {
        List<Assignment> assignments = assignmentService.getAllAssignments();
        return ResponseEntity.ok(assignments);
    }

    @GetMapping("/assignments/{id}")
    public ResponseEntity<Assignment> getAssignmentById(@PathVariable Long id) {
        Assignment assignment = assignmentService.getAssignmentById(id);
        return ResponseEntity.ok(assignment);
    }

    @PutMapping("/assignments/{id}")
    public ResponseEntity<Assignment> updateAssignment(@PathVariable Long id, @RequestBody Assignment assignment) {
        Assignment updatedAssignment = assignmentService.updateAssignment(id, assignment);
        return ResponseEntity.ok(updatedAssignment);
    }

    @DeleteMapping("/assignments/{id}")
    public ResponseEntity<Void> deleteAssignmentById(@PathVariable Long id) {
        assignmentService.deleteAssignment(id);
        return ResponseEntity.noContent().build();
    }

    // Event Endpoints
    @PostMapping("/events")
    public ResponseEntity<Events> createEvent(@RequestBody Events events) {
        Events createdEvent = eventsService.createEvent(events);
        return ResponseEntity.ok(createdEvent);
    }

    @GetMapping("/events")
    public ResponseEntity<List<Events>> getAllEvents() {
        List<Events> events = eventsService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<Events> getEventById(@PathVariable Long id) {
        Events events = eventsService.getEventsById(id);
        return ResponseEntity.ok(events);
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<Events> updateEvent(@PathVariable Long id, @RequestBody Events events) {
        Events updatedEvent = eventsService.updateEvents(id, events);
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<Void> deleteEventById(@PathVariable Long id) {
        eventsService.deleteEvents(id);
        return ResponseEntity.noContent().build();
    }
}
