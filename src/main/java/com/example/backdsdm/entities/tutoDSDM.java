package com.example.backdsdm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "tutoDSDM")
public class tutoDSDM {
    @Id
    @Setter(AccessLevel.NONE)
    ObjectId idTuto;
    String nomTuto;
    //@OneToMany(mappedBy = "tuto")
    //@JsonIgnore
    @DBRef(lazy = true)
    @JsonIgnore
    List<Composant> composants;
}
