package com.example.backdsdm.services;

import com.example.backdsdm.entities.DeploymentPlan;

import java.util.List;

public interface IDeploymentPlanService {
    List<DeploymentPlan> getDeploymentPlansByProjectId(String projectId);
    DeploymentPlan addDeploymentPlan(String projectId, String environment, String dataMigration, String preProdTests);

}
