package com.example.backdsdm.testIntegration.service;

import com.example.backdsdm.Repositories.DeploymentPlanRepository;
import com.example.backdsdm.entities.DeploymentPlan;
import com.example.backdsdm.services.DeploymentPlanServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class DeploymentPlanServiceImplIntegrationTest {

    @Autowired
    private DeploymentPlanServiceImpl deploymentPlanService;

    @Autowired
    private DeploymentPlanRepository deploymentPlanRepository;
    @BeforeEach
    public void setUp() {
        deploymentPlanRepository.deleteAll();
    }
    @Test
    public void testAddDeploymentPlan() {
        // Créer un nouveau DeploymentPlan
        DeploymentPlan deploymentPlan = deploymentPlanService.addDeploymentPlan(
                "projectId123", "Production", "Data Migration Steps", "Pre-Prod Test Steps"
        );

        // Vérification
        assertThat(deploymentPlan).isNotNull();
        assertThat(deploymentPlan.getProjectId()).isEqualTo("projectId123");
        assertThat(deploymentPlan.getEnvironment()).isEqualTo("Production");
        assertThat(deploymentPlan.getDataMigration()).isEqualTo("Data Migration Steps");
        assertThat(deploymentPlan.getPreProdTests()).isEqualTo("Pre-Prod Test Steps");
        assertThat(deploymentPlan.getReleases()).isEmpty();
    }

    @Test
    public void testGetDeploymentPlansByProjectId() {
        // Ajouter un nouveau plan de déploiement
        deploymentPlanService.addDeploymentPlan("projectId123", "Prod", "Migration1", "Test1");
        deploymentPlanService.addDeploymentPlan("projectId123", "Staging", "Migration2", "Test2");

        // Récupérer les plans de déploiement
        List<DeploymentPlan> foundDeploymentPlans = deploymentPlanService.getDeploymentPlansByProjectId("projectId123");

        // Vérification
        assertThat(foundDeploymentPlans).hasSize(2);
        assertThat(foundDeploymentPlans.get(0).getEnvironment()).isEqualTo("Prod");
        assertThat(foundDeploymentPlans.get(1).getEnvironment()).isEqualTo("Staging");
    }
}
