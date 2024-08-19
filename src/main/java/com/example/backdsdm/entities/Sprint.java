package com.example.backdsdm.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "Sprint")
public class Sprint {
    @Id
    @Setter(AccessLevel.NONE)
    private String id;
    private String name;
    private String projectId;
    private boolean archived = false;

    private Set<Iteration> iterations;
}
