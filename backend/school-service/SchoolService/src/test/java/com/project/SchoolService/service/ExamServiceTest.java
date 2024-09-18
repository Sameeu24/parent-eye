package com.project.SchoolService.service;

import com.project.SchoolService.domain.Exam;
import com.project.SchoolService.repository.ExamRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExamServiceTest {

    @InjectMocks
    private ExamService examService;

    @Mock
    private ExamRepository examRepository;

    public ExamServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createExam() {
        Exam exam = new Exam();
        exam.setId(1L);
        exam.setSubject("Math");
        exam.setScores(90);

        when(examRepository.save(any(Exam.class))).thenReturn(exam);

        Exam createdExam = examService.createExam(exam);
        assertEquals("Math", createdExam.getSubject());
        assertEquals(90, createdExam.getScores());
        verify(examRepository, times(1)).save(exam);
    }

    @Test
    void getAllExams() {
        when(examRepository.findAll()).thenReturn(Collections.singletonList(new Exam()));

        assertFalse(examService.getAllExams().isEmpty());
        verify(examRepository, times(1)).findAll();
    }

    @Test
    void getExamById_found() {
        Exam exam = new Exam();
        exam.setId(1L);
        when(examRepository.findById(1L)).thenReturn(Optional.of(exam));

        Exam foundExam = examService.getExamById(1L);
        assertNotNull(foundExam);
        assertEquals(1L, foundExam.getId());
        verify(examRepository, times(1)).findById(1L);
    }

    @Test
    void getExamById_notFound() {
        when(examRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            examService.getExamById(1L);
        });
        assertEquals("Exam not found with id 1", exception.getMessage());
    }

    @Test
    void updateExam_found() {
        Exam existingExam = new Exam();
        existingExam.setId(1L);
        existingExam.setSubject("Math");
        existingExam.setScores(90);

        Exam updatedExam = new Exam();
        updatedExam.setSubject("Science");
        updatedExam.setScores(85);

        when(examRepository.existsById(1L)).thenReturn(true);
        when(examRepository.save(any(Exam.class))).thenReturn(updatedExam);

        Exam result = examService.updateExam(1L, updatedExam);
        assertEquals("Science", result.getSubject());
        assertEquals(85, result.getScores());
        verify(examRepository, times(1)).save(updatedExam);
    }

    @Test
    void updateExam_notFound() {
        Exam updatedExam = new Exam();
        updatedExam.setId(1L);

        when(examRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            examService.updateExam(1L, updatedExam);
        });
        assertEquals("Exam not found with id 1", exception.getMessage());
    }

    @Test
    void deleteExam_found() {
        when(examRepository.existsById(1L)).thenReturn(true);

        examService.deleteExam(1L);
        verify(examRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteExam_notFound() {
        when(examRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            examService.deleteExam(1L);
        });
        assertEquals("Exam not found with id 1", exception.getMessage());
    }
}
