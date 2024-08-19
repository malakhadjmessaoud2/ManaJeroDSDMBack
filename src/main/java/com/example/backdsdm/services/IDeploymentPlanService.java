package com.example.backdsdm.services;

import com.example.backdsdm.entities.DeploymentPlan;

import java.util.List;

public interface IDeploymentPlanService {
    List<DeploymentPlan> getDeploymentPlansByProjectId(String projectId);
    DeploymentPlan addDeploymentPlan(String projectId, String environment, String dataMigration, String preProdTests);

    DeploymentPlan updateDeploymentPlan(String projectId, String id, String environment, String dataMigration, String preProdTests);

    DeploymentPlan archiveDeploymentPlan(String projectId, String id);

    void deleteDeploymentPlan(String projectId, String id);
}
