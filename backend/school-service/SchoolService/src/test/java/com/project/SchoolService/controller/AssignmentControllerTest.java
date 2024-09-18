package com.project.SchoolService.controller;

import com.project.SchoolService.domain.Assignment;
import com.project.SchoolService.service.AssignmentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AssignmentControllerTest {

    @InjectMocks
    private AssignmentController assignmentController;

    @Mock
    private AssignmentService assignmentService;

    public AssignmentControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAssignment() {
        Assignment assignment = new Assignment();
        assignment.setId(1L);
        assignment.setSubject("Math");
        assignment.setContent("Algebra");

        when(assignmentService.createAssignment(any(Assignment.class))).thenReturn(assignment);

        ResponseEntity<Assignment> response = assignmentController.createAssignment(assignment);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Math", response.getBody().getSubject());
        verify(assignmentService, times(1)).createAssignment(assignment);
    }

    @Test
    void getAllAssignments() {
        when(assignmentService.getAllAssignments()).thenReturn(Collections.singletonList(new Assignment()));

        ResponseEntity<List<Assignment>> response = assignmentController.getAllAssignments();
        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isEmpty());
        verify(assignmentService, times(1)).getAllAssignments();
    }

    @Test
    void getAssignmentById_found() {
        Assignment assignment = new Assignment();
        assignment.setId(1L);
        when(assignmentService.getAssignmentById(1L)).thenReturn(assignment);

        ResponseEntity<Assignment> response = assignmentController.getAssignmentById(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
        verify(assignmentService, times(1)).getAssignmentById(1L);
    }

    @Test
    void getAssignmentById_notFound() {
        when(assignmentService.getAssignmentById(1L)).thenThrow(new RuntimeException("Assignment not found with id :1"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            assignmentController.getAssignmentById(1L);
        });
        assertEquals("Assignment not found with id :1", exception.getMessage());
    }

    @Test
    void updateAssignment_found() {
        Assignment assignment = new Assignment();
        assignment.setId(1L);
        assignment.setSubject("Math");

        when(assignmentService.updateAssignment(1L, assignment)).thenReturn(assignment);

        ResponseEntity<Assignment> response = assignmentController.updateAssignment(1L, assignment);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
        verify(assignmentService, times(1)).updateAssignment(1L, assignment);
    }

    @Test
    void updateAssignment_notFound() {
        Assignment assignment = new Assignment();
        assignment.setId(1L);

        when(assignmentService.updateAssignment(1L, assignment)).thenThrow(new RuntimeException("Assignment not found with id :1"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            assignmentController.updateAssignment(1L, assignment);
        });
        assertEquals("Assignment not found with id :1", exception.getMessage());
    }

    @Test
    void deleteAssignment_found() {
        doNothing().when(assignmentService).deleteAssignment(1L);

        ResponseEntity<Void> response = assignmentController.deleteAssignmentById(1L);
        assertEquals(204, response.getStatusCodeValue());
        verify(assignmentService, times(1)).deleteAssignment(1L);
    }

    @Test
    void deleteAssignment_notFound() {
        doThrow(new RuntimeException("Assignment not found with id :1")).when(assignmentService).deleteAssignment(1L);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            assignmentController.deleteAssignmentById(1L);
        });
        assertEquals("Assignment not found with id :1", exception.getMessage());
    }
}
