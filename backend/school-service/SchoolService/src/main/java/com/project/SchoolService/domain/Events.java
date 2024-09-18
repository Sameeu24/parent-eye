package com.project.SchoolService.domain;

//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "events")
public class Events {
    @Id

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
