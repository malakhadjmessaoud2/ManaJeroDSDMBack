package com.example.backdsdm.entities;

import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "Foundation")
public class Foundation {
    @Id
    private String id;
    private String projectVision;
    private String userNeeds;
    private String projectCharter;
    private String requirements;
    private String idUser;
    private String project;
}
