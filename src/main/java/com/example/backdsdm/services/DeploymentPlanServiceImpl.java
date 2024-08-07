package com.example.backdsdm.services;

import com.example.backdsdm.Repositories.DeploymentPlanRepository;
import com.example.backdsdm.entities.DeploymentPlan;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeploymentPlanServiceImpl implements IDeploymentPlanService {
private final DeploymentPlanRepository deploymentPlanRepository;

    @Override
    public List<DeploymentPlan> getDeploymentPlansByProjectId(String projectId) {
        return deploymentPlanRepository.findByProjectId(projectId);
    }

    @Override
    public DeploymentPlan addDeploymentPlan(String projectId, String environment, String dataMigration, String preProdTests) {
        DeploymentPlan deploymentPlan = new DeploymentPlan();
        deploymentPlan.setProjectId(projectId);
        deploymentPlan.setEnvironment(environment);
        deploymentPlan.setDataMigration(dataMigration);
        deploymentPlan.setPreProdTests(preProdTests);
        deploymentPlan.setReleases(new HashSet<>());
        return deploymentPlanRepository.save(deploymentPlan);
    }
}
