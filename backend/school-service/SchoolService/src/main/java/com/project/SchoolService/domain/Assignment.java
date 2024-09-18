package com.project.SchoolService.domain;

//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "assignments")
public class Assignment {

    @Id


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
