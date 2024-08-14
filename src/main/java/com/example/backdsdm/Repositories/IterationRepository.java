package com.example.backdsdm.Repositories;

import com.example.backdsdm.entities.Iteration;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface IterationRepository extends MongoRepository<Iteration, String> {
    List<Iteration> findBySprintId(String sprintId);
    Optional<Iteration> findBySprintIdAndId(String sprintId, String id);

}
