package com.example.backdsdm.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "Dsdm")
public class PresProjet {
    @Id
    String id;
    String context;
    String priorisation;
    String status;
    LocalDate startDate;
    LocalDate endDate;
    String id_user;
    String idproject;


}
