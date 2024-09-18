package com.project.SchoolService.repository;

import com.project.SchoolService.domain.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
}
