package com.example.backdsdm.Repositories;

import com.example.backdsdm.entities.Feasibility;
import com.example.backdsdm.entities.PresProjet;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FeasibilityRepository extends MongoRepository<Feasibility, ObjectId> {
    public Feasibility findByIdproject(String idproject);
    Optional<Feasibility> findByIdAndIdproject(String id, String idProject);

}
