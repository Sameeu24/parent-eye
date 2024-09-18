package com.project.SchoolService.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "events")
public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String subject;
    private String description;
    private Date date;
    private enum status{
        completed,
        upcoming,
        notAttended
    }

}
