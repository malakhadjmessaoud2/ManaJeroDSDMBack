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
@Document(collection = "Advantage")
public class Advantage {
    @Id
    //@Setter(AccessLevel.NONE)
    String id;
    String titre;
    String desc;
    byte[] imageUrl;
}
