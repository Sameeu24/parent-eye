package com.project.SchoolService.domain;

//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "exams")
public class Exam {

    @Id


    private Long id;
    private String subject;
    private int scores;
    private enum status{
        passed,
        failed
    }
}
