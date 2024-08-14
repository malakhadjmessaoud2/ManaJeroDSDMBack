package com.example.backdsdm.Repositories;

import com.example.backdsdm.entities.Release;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ReleaseRepository extends MongoRepository<Release, String> {
    List<Release> findByDeploymentPlanId(String deploymentPlanId);
    Optional<Release> findByDeploymentPlanIdAndId(String deploymentPlanId, String id);

}
