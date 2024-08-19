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
@Document(collection = "KPI")
public class KPI {
    @Id
    @Setter(AccessLevel.NONE)
    private String id;
    private String name;
    private String value;
    private String projectId;
    private boolean archived = false;

}
