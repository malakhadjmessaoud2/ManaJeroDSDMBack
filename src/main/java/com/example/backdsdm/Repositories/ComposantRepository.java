package com.example.backdsdm.Repositories;

import com.example.backdsdm.entities.Composant;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ComposantRepository extends MongoRepository<Composant, ObjectId> {
}
