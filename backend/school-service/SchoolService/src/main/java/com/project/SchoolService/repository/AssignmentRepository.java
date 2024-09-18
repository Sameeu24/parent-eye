package com.project.SchoolService.repository;

import com.project.SchoolService.domain.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
}
