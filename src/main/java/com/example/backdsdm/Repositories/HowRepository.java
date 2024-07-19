package com.example.backdsdm.Repositories;

import com.example.backdsdm.entities.How;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HowRepository extends MongoRepository<How, ObjectId> {
}
