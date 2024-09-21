package com.project.SchoolService.controller;

import com.project.SchoolService.domain.Exam;
import com.project.SchoolService.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scores")
public class ExamController {
    @Autowired
    private final ExamService examService;
    public ExamController(ExamService examService) {
        this.examService = examService;
    }
    @PostMapping
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam){
        Exam exam1 = examService.createExam(exam);
        return ResponseEntity.ok(exam1);
    }
    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Exam> getExamById(@PathVariable Long id) {
        Exam exam = examService.getExamById(id);
        return ResponseEntity.ok(exam);
    }
    //GET ALL
    @GetMapping
    public ResponseEntity<List<Exam>> getAllExams() {
        List<Exam> exams = examService.getAllExams();
        return ResponseEntity.ok(exams);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Exam> updateExam(@PathVariable Long id, @RequestBody Exam exam) {
        Exam updatedExam = examService.updateExam(id, exam);
        return ResponseEntity.ok(updatedExam);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
        return ResponseEntity.noContent().build();
    }

}
