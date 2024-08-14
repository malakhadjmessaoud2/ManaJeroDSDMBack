package com.example.backdsdm.Repositories;

import com.example.backdsdm.entities.ImprovementPlan;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ImprovementPlanRepository extends MongoRepository<ImprovementPlan, String> {
    List<ImprovementPlan> findByProjectId(String projectId);
    Optional<ImprovementPlan> findByProjectIdAndId(String projectId, String id);

}
