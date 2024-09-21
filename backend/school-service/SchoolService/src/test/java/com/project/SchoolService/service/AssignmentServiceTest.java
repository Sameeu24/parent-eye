package com.project.SchoolService.service;

import com.project.SchoolService.domain.Assignment;
import com.project.SchoolService.repository.AssignmentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AssignmentServiceTest {

    @InjectMocks
    private AssignmentService assignmentService;

    @Mock
    private AssignmentRepository assignmentRepository;

    public AssignmentServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAssignment() {
        Assignment assignment = new Assignment();
        assignment.setId(1L);
        assignment.setSubject("Math");
        assignment.setContent("Algebra");

        when(assignmentRepository.save(any(Assignment.class))).thenReturn(assignment);

        Assignment createdAssignment = assignmentService.createAssignment(assignment);
        assertEquals("Math", createdAssignment.getSubject());
        verify(assignmentRepository, times(1)).save(assignment);
    }

    @Test
    void getAllAssignments() {
        when(assignmentRepository.findAll()).thenReturn(Collections.singletonList(new Assignment()));

        assertFalse(assignmentService.getAllAssignments().isEmpty());
        verify(assignmentRepository, times(1)).findAll();
    }

    @Test
    void getAssignmentById_found() {
        Assignment assignment = new Assignment();
        assignment.setId(1L);
        when(assignmentRepository.findById(1L)).thenReturn(Optional.of(assignment));

        Assignment foundAssignment = assignmentService.getAssignmentById(1L);
        assertNotNull(foundAssignment);
        assertEquals(1L, foundAssignment.getId());
        verify(assignmentRepository, times(1)).findById(1L);
    }

    @Test
    void getAssignmentById_notFound() {
        when(assignmentRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            assignmentService.getAssignmentById(1L);
        });
        assertEquals("Assignment not found with id :1", exception.getMessage());
    }

    @Test
    void updateAssignment_found() {
        Assignment existingAssignment = new Assignment();
        existingAssignment.setId(1L);
        existingAssignment.setSubject("Math");
        existingAssignment.setContent("Algebra");

        Assignment updatedAssignment = new Assignment();
        updatedAssignment.setId(1L);
        updatedAssignment.setSubject("Science");
        updatedAssignment.setContent("Biology");

        when(assignmentRepository.existsById(1L)).thenReturn(true);
        when(assignmentRepository.save(any(Assignment.class))).thenReturn(updatedAssignment);

        Assignment result = assignmentService.updateAssignment(1L, updatedAssignment);
        assertEquals("Science", result.getSubject());
        verify(assignmentRepository, times(1)).save(updatedAssignment);
    }

    @Test
    void updateAssignment_notFound() {
        Assignment updatedAssignment = new Assignment();
        updatedAssignment.setId(1L);

        when(assignmentRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            assignmentService.updateAssignment(1L, updatedAssignment);
        });
        assertEquals("Assignment not found with id :1", exception.getMessage());
    }

    @Test
    void deleteAssignment_found() {
        when(assignmentRepository.existsById(1L)).thenReturn(true);

        assignmentService.deleteAssignment(1L);
        verify(assignmentRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteAssignment_notFound() {
        when(assignmentRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            assignmentService.deleteAssignment(1L);
        });
        assertEquals("Assignment not found with id :1", exception.getMessage());
    }
}
