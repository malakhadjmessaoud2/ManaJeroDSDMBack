package com.example.backdsdm.Repositories;

import com.example.backdsdm.entities.DeploymentPlan;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DeploymentPlanRepository extends MongoRepository<DeploymentPlan, String> {
    List<DeploymentPlan> findByProjectId(String projectId);
}
