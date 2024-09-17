package com.project.SchoolService.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table

public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String subject;
    private String content;
    private Date date;
    private enum status{
        upcoming,
        completed,
        pending
    };

}
