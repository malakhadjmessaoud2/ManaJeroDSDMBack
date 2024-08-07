package com.example.backdsdm.entities;

import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "DeploymentPlan")
public class DeploymentPlan {
    @Id
    @Setter(AccessLevel.NONE)
    private String id;
    private String environment;
    private String dataMigration;
    private String preProdTests;
    private String projectId;

    private Set<Release> releases;
}
