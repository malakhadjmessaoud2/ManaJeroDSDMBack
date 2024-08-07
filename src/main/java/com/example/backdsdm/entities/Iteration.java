package com.example.backdsdm.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "Iteration")
public class Iteration {
    @Id
    @Setter(AccessLevel.NONE)
    private String id;
    private String feature;
    private String deliverables;
    private String sprintId;

}
