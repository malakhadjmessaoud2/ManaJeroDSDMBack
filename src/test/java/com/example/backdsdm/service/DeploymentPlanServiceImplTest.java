
package com.example.backdsdm.service;

        import com.example.backdsdm.Repositories.DeploymentPlanRepository;
        import com.example.backdsdm.entities.DeploymentPlan;
        import com.example.backdsdm.services.DeploymentPlanServiceImpl;
        import org.junit.jupiter.api.Assertions;
        import org.junit.jupiter.api.Test;
        import org.junit.jupiter.api.extension.ExtendWith;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.junit.jupiter.MockitoExtension;

        import java.util.Arrays;
        import java.util.HashSet;
        import java.util.List;

        import static org.mockito.ArgumentMatchers.any;
        import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeploymentPlanServiceImplTest {

    @Mock
    private DeploymentPlanRepository deploymentPlanRepository;

    @InjectMocks
    private DeploymentPlanServiceImpl deploymentPlanService;

    @Test
    public void testGetDeploymentPlansByProjectId() {
        // Create a sample DeploymentPlan
        DeploymentPlan deploymentPlan1 = new DeploymentPlan();
        deploymentPlan1.setProjectId("projectId123");

        DeploymentPlan deploymentPlan2 = new DeploymentPlan();
        deploymentPlan2.setProjectId("projectId123");

        List<DeploymentPlan> deploymentPlans = Arrays.asList(deploymentPlan1, deploymentPlan2);

        // Mock the repository behavior
        when(deploymentPlanRepository.findByProjectId("projectId123")).thenReturn(deploymentPlans);

        // Call the method under test
        List<DeploymentPlan> foundDeploymentPlans = deploymentPlanService.getDeploymentPlansByProjectId("projectId123");

        // Assertions
        Assertions.assertNotNull(foundDeploymentPlans, "DeploymentPlans should not be null");
        Assertions.assertEquals(2, foundDeploymentPlans.size(), "The number of DeploymentPlans should match");
    }

    @Test
    public void testAddDeploymentPlan() {
        // Create a sample DeploymentPlan
        DeploymentPlan deploymentPlan = new DeploymentPlan();
        deploymentPlan.setProjectId("projectId123");
        deploymentPlan.setEnvironment("Production");
        deploymentPlan.setDataMigration("Data Migration Steps");
        deploymentPlan.setPreProdTests("Pre-Prod Test Steps");
        deploymentPlan.setReleases(new HashSet<>());

        // Mock the repository behavior
        when(deploymentPlanRepository.save(any(DeploymentPlan.class))).thenReturn(deploymentPlan);

        // Call the method under test
        DeploymentPlan savedDeploymentPlan = deploymentPlanService.addDeploymentPlan(
                "projectId123", "Production", "Data Migration Steps", "Pre-Prod Test Steps"
        );

        // Assertions
        Assertions.assertNotNull(savedDeploymentPlan, "Saved DeploymentPlan should not be null");
        Assertions.assertEquals("projectId123", savedDeploymentPlan.getProjectId(), "Project ID mismatch");
        Assertions.assertEquals("Production", savedDeploymentPlan.getEnvironment(), "Environment mismatch");
        Assertions.assertEquals("Data Migration Steps", savedDeploymentPlan.getDataMigration(), "Data Migration mismatch");
        Assertions.assertEquals("Pre-Prod Test Steps", savedDeploymentPlan.getPreProdTests(), "Pre-Prod Tests mismatch");
        Assertions.assertEquals(0, savedDeploymentPlan.getReleases().size(), "Releases set should be empty");
    }
}
