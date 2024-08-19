package com.example.backdsdm.Repositories;

import com.example.backdsdm.entities.Sprint;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface SprintRepository extends MongoRepository<Sprint, String> {
    List<Sprint> findByProjectId(String projectId);
    Optional<Sprint> findByProjectIdAndId(String projectId, String id);

}
