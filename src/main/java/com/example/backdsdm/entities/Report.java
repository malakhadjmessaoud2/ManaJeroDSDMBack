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
@Document(collection = "Report")
public class Report {
    @Id
    @Setter(AccessLevel.NONE)
    private String id;
    private String title;
    private String content;
    private String projectId;
}
