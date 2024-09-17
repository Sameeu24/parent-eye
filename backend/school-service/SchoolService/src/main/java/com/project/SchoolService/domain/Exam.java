package com.project.SchoolService.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table

public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String subject;
    private int scores;
    private enum status{
        passed,
        failed
    }
}
