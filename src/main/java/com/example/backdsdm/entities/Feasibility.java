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
@Document(collection = "Feasibility")
public class Feasibility {
    @Id
    //@Setter(AccessLevel.NONE)
    private String id;

    private String technicalFeasibility;
    private String commercialFeasibility;
    private String mvp;
    private String releaseBoard;
    private String viability;
    String id_user;
    String idproject;

}
