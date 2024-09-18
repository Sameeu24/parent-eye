package com.project.SchoolService.controller;


import com.project.SchoolService.domain.Assignment;
import com.project.SchoolService.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignment")
public class AssignmentController {
    @Autowired
    private final AssignmentService assignmentService;
    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }
    @PostMapping("/{id}")
    public ResponseEntity<Assignment> createAssignment(@RequestBody Assignment assignment){
        Assignment assignment1 = assignmentService.createAssignment(assignment);
        return ResponseEntity.ok(assignment1);
    }
    @GetMapping
    public ResponseEntity<List<Assignment>> getAllAssignments(){
        List<Assignment> assignments = assignmentService.getAllAssignments();
        return ResponseEntity.ok(assignments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assignment> getAssignmentById(@PathVariable Long id){
        Assignment assignment = assignmentService.getAssignmentById(id);
        return ResponseEntity.ok(assignment);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Assignment> updateAssignment(@PathVariable Long id, @RequestBody Assignment assignment){
        Assignment updateAssignment = assignmentService.updateAssignment(id,assignment);
        return ResponseEntity.ok(updateAssignment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignmentById(@PathVariable Long id){
        assignmentService.deleteAssignment(id);
        return ResponseEntity.noContent().build();
    }
}
