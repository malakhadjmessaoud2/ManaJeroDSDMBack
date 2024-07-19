package com.example.backdsdm.entities;

import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "Composant")
public class Composant {
    @Id
    @Setter(AccessLevel.NONE)
    String id;
    String nomComposant;
    String titre;
    String desc;
    String typeAff;
    byte[] imageUrl;
    //@ManyToOne
    @DBRef
    tutoDSDM tuto  ;

}
