package com.example.backdsdm.Repositories;

import com.example.backdsdm.entities.Advantage;
import com.example.backdsdm.entities.Introduction;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdvantageRepository extends MongoRepository<Advantage, ObjectId> {
}
