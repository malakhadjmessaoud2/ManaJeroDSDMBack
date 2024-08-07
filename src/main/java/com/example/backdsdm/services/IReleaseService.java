package com.example.backdsdm.services;

import com.example.backdsdm.entities.Release;

import java.util.List;

public interface IReleaseService {
    List<Release> getReleasesByDeploymentPlanId(String deploymentPlanId);
    Release addRelease(String deploymentPlanId, String name, String details);

}
