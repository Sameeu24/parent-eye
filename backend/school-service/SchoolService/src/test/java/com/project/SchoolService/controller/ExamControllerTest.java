package com.project.SchoolService.controller;

import com.project.SchoolService.domain.Exam;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExamControllerTest {

    @InjectMocks
    private ExamController examController;

    @Mock
    private ExamService examService;

    public ExamControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createExam() {
        Exam exam = new Exam();
        exam.setId(1L);
        exam.setSubject("Math");

        when(examService.createExam(any(Exam.class))).thenReturn(exam);

        ResponseEntity<Exam> response = examController.createExam(exam);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Math", response.getBody().getSubject());
        verify(examService, times(1)).createExam(exam);
    }

    @Test
    void getExamById_found() {
        Exam exam = new Exam();
        exam.setId(1L);
        when(examService.getExamById(1L)).thenReturn(exam);

        ResponseEntity<Exam> response = examController.getExamById(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
        verify(examService, times(1)).getExamById(1L);
    }

    @Test
    void getExamById_notFound() {
        when(examService.getExamById(1L)).thenThrow(new RuntimeException("Exam not found with id 1"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            examController.getExamById(1L);
        });
        assertEquals("Exam not found with id 1", exception.getMessage());
    }

    @Test
    void getAllExams() {
        when(examService.getAllExams()).thenReturn(Collections.singletonList(new Exam()));

        ResponseEntity<List<Exam>> response = examController.getAllExams();
        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isEmpty());
        verify(examService, times(1)).getAllExams();
    }

    @Test
    void updateExam_found() {
        Exam exam = new Exam();
        exam.setId(1L);
        exam.setSubject("Math");

        when(examService.updateExam(1L, exam)).thenReturn(exam);

        ResponseEntity<Exam> response = examController.updateExam(1L, exam);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
        verify(examService, times(1)).updateExam(1L, exam);
    }

    @Test
    void updateExam_notFound() {
        Exam exam = new Exam();
        exam.setId(1L);

        when(examService.updateExam(1L, exam)).thenThrow(new RuntimeException("Exam not found with id 1"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            examController.updateExam(1L, exam);
        });
        assertEquals("Exam not found with id 1", exception.getMessage());
    }

    @Test
    void deleteExam_found() {
        doNothing().when(examService).deleteExam(1L);

        ResponseEntity<Void> response = examController.deleteExam(1L);
        assertEquals(204, response.getStatusCodeValue());
        verify(examService, times(1)).deleteExam(1L);
    }

    @Test
    void deleteExam_notFound() {
        doThrow(new RuntimeException("Exam not found with id 1")).when(examService).deleteExam(1L);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            examController.deleteExam(1L);
        });
        assertEquals("Exam not found with id 1", exception.getMessage());
    }
}
