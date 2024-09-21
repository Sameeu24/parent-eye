package com.project.SchoolService.service;

import com.project.SchoolService.domain.Exam;
import com.project.SchoolService.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ExamService {

    private final ExamRepository examRepository;
    @Autowired
    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }
    public Exam createExam(Exam exam){
        return examRepository.save(exam);
    }
    // READ
    public Exam getExamById(Long id) {
        Optional<Exam> exam = examRepository.findById(id);
        if (exam.isPresent()) {
            return exam.get();
        } else {
            throw new RuntimeException("Exam not found with id " + id);
        }
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    // UPDATE
    public Exam updateExam(Long id, Exam exam) {
        if (examRepository.existsById(id)) {
            exam.setId(id);
            return examRepository.save(exam);
        } else {
            throw new RuntimeException("Exam not found with id " + id);
        }
    }

    // DELETE
    public void deleteExam(Long id) {
        if (examRepository.existsById(id)) {
            examRepository.deleteById(id);
        } else {
            throw new RuntimeException("Exam not found with id " + id);
        }
    }
}
