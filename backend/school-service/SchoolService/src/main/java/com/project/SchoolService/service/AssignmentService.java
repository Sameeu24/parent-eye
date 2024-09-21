package com.project.SchoolService.service;


import com.project.SchoolService.domain.Assignment;

import com.project.SchoolService.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    @Autowired
    public AssignmentService(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    //CREATE
    public Assignment createAssignment(Assignment assignment){
        return assignmentRepository.save(assignment);
    }
    // FIND ALL
    public List<Assignment> getAllAssignments(){
        return assignmentRepository.findAll();
    }
    // FIND BY ID
    public Assignment getAssignmentById(Long id){
        Optional<Assignment> assignment = assignmentRepository.findById(id);
        if(assignment.isPresent()){
            return assignment.get();
        }else{
            throw new RuntimeException("Assignment not found with id :" + id);
        }
    }
    //UPDATE
    public Assignment updateAssignment(Long id, Assignment assignment){
        if (assignmentRepository.existsById(id)){
            assignment.setId(id);
            return assignmentRepository.save(assignment);
        }else {
            throw new RuntimeException("Assignment not found with id :" + id);
        }
    }
    //DELETE
    public void deleteAssignment(Long id){
        if(assignmentRepository.existsById(id)){
            assignmentRepository.deleteById(id);
        }else{
            throw new RuntimeException("Assignment not found with id :" + id);
        }
    }
}
