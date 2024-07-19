package com.example.backdsdm.Repositories;

import com.example.backdsdm.entities.WhatIf;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WhatIfRepository extends MongoRepository<WhatIf, ObjectId> {
}
