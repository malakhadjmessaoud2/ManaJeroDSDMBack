package com.example.backdsdm.Repositories;

import com.example.backdsdm.entities.Foundation;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FoundationRepository extends MongoRepository<Foundation, String> {
    Foundation findByProject(String project);
    Optional<Foundation> findByIdAndProject(String id, String project);

}
