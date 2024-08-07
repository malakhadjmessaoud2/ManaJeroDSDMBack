package com.example.backdsdm.Repositories;

import com.example.backdsdm.entities.ImprovementPlan;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ImprovementPlanRepository extends MongoRepository<ImprovementPlan, String> {
    List<ImprovementPlan> findByProjectId(String projectId);
}
