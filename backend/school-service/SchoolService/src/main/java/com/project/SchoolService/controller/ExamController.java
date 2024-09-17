package com.project.SchoolService.controller;

import com.project.SchoolService.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scores")
public class ExamController {

    @Autowired
    private final ExamService examService;
    public ExamController(ExamService examService) {
        this.examService = examService;
    }
}
