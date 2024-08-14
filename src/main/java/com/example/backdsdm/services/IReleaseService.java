package com.example.backdsdm.services;

import com.example.backdsdm.entities.Release;

import java.util.List;

public interface IReleaseService {
    List<Release> getReleasesByDeploymentPlanId(String deploymentPlanId);
    Release addRelease(String deploymentPlanId, String name, String details);

    Release updateRelease(String deploymentPlanId, String id, String name, String details);

    Release archiveRelease(String deploymentPlanId, String id);

    void deleteRelease(String deploymentPlanId, String id);
}
