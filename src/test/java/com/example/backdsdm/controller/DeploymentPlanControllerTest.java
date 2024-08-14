package com.example.backdsdm.controller;

import com.example.backdsdm.Controller.DeploymentPlanController;
import com.example.backdsdm.entities.DeploymentPlan;
import com.example.backdsdm.services.IDeploymentPlanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DeploymentPlanControllerTest {

    @Mock
    private IDeploymentPlanService deploymentPlanService;

    @InjectMocks
    private DeploymentPlanController deploymentPlanController;

    @BeforeEach
    public void setUp() {
        // Initialization logic if needed
    }

    @Test
    public void testGetDeploymentPlansByProjectId() {
        // Create sample DeploymentPlans
        DeploymentPlan deploymentPlan1 = new DeploymentPlan();
        deploymentPlan1.setProjectId("projectId123");

        DeploymentPlan deploymentPlan2 = new DeploymentPlan();
        deploymentPlan2.setProjectId("projectId123");

        List<DeploymentPlan> deploymentPlans = Arrays.asList(deploymentPlan1, deploymentPlan2);

        // Mock the service behavior
        when(deploymentPlanService.getDeploymentPlansByProjectId("projectId123")).thenReturn(deploymentPlans);

        // Call the controller method
        List<DeploymentPlan> foundDeploymentPlans = deploymentPlanController.getDeploymentPlansByProjectId("projectId123");

        // Assertions
        assertNotNull(foundDeploymentPlans, "DeploymentPlans should not be null");
        assertEquals(2, foundDeploymentPlans.size(), "The number of DeploymentPlans should match");
    }

    @Test
    public void testAddDeploymentPlan() {
        // Create a sample DeploymentPlan
        DeploymentPlan deploymentPlan = new DeploymentPlan();
        deploymentPlan.setProjectId("projectId123");
        deploymentPlan.setEnvironment("Production");
        deploymentPlan.setDataMigration("Data Migration Steps");
        deploymentPlan.setPreProdTests("Pre-Prod Test Steps");

        // Mock the service behavior
        when(deploymentPlanService.addDeploymentPlan(
                anyString(),
                anyString(),
                anyString(),
                anyString()
        )).thenReturn(deploymentPlan);

        // Call the controller method
        DeploymentPlan savedDeploymentPlan = deploymentPlanController.addDeploymentPlan(
                "projectId123",
                "Production",
                "Data Migration Steps",
                "Pre-Prod Test Steps"
        );

        // Assertions
        assertNotNull(savedDeploymentPlan, "Saved DeploymentPlan should not be null");
        assertEquals("projectId123", savedDeploymentPlan.getProjectId(), "Project ID mismatch");
        assertEquals("Production", savedDeploymentPlan.getEnvironment(), "Environment mismatch");
        assertEquals("Data Migration Steps", savedDeploymentPlan.getDataMigration(), "Data Migration mismatch");
        assertEquals("Pre-Prod Test Steps", savedDeploymentPlan.getPreProdTests(), "Pre-Prod Tests mismatch");
    }
}
