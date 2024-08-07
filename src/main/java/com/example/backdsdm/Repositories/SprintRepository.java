package com.example.backdsdm.Repositories;

import com.example.backdsdm.entities.Sprint;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SprintRepository extends MongoRepository<Sprint, String> {
    List<Sprint> findByProjectId(String projectId);
}
