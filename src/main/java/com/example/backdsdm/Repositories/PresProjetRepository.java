package com.example.backdsdm.Repositories;

import com.example.backdsdm.entities.PresProjet;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PresProjetRepository extends MongoRepository<PresProjet, ObjectId> {
 public PresProjet findByIdproject(String idproject);
 Optional<PresProjet> findByIdAndIdproject(String id, String idproject);

}
