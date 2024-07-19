package com.example.backdsdm.Repositories;

import com.example.backdsdm.entities.Why;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WhyRepository extends MongoRepository<Why, ObjectId> {
}
