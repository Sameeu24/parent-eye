package com.project.screentime_service.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "links")
public class Link {

    @Id
    private Long id;
    private String url;


    @Enumerated(EnumType.STRING)
    private LinkStatus linkStatus;

}
