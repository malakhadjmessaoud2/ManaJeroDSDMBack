package com.example.backdsdm.Repositories;

import com.example.backdsdm.entities.Foundation;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FoundationRepository extends MongoRepository<Foundation, ObjectId> {
    Foundation findByProject(String projectId);
    Optional<Foundation> findByIdAndProject(String id, String project);

}
