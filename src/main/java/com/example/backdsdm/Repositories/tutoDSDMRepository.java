package com.example.backdsdm.Repositories;

import com.example.backdsdm.entities.tutoDSDM;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface tutoDSDMRepository extends MongoRepository<tutoDSDM, ObjectId> {
}
