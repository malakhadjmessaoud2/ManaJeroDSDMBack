package com.example.backdsdm.service;

import com.example.backdsdm.Repositories.ImprovementPlanRepository;
import com.example.backdsdm.entities.ImprovementPlan;
import com.example.backdsdm.services.ImprovementPlanServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ImprovementPlanServiceImplTest {

    @Mock
    private ImprovementPlanRepository improvementPlanRepository;

    @InjectMocks
    private ImprovementPlanServiceImpl improvementPlanService;

    @Test
    public void testGetImprovementPlansByProjectId() {
        // Create a sample list of ImprovementPlans
        ImprovementPlan plan1 = new ImprovementPlan();
        plan1.setProjectId("456");
        plan1.setContent("Improvement Plan 1");

        ImprovementPlan plan2 = new ImprovementPlan();
        plan2.setProjectId("456");
        plan2.setContent("Improvement Plan 2");

        List<ImprovementPlan> improvementPlans = Arrays.asList(plan1, plan2);

        // Mock the repository behavior
        when(improvementPlanRepository.findByProjectId("456")).thenReturn(improvementPlans);

        // Call the method under test
        List<ImprovementPlan> retrievedPlans = improvementPlanService.getImprovementPlansByProjectId("456");

        // Assertions
        Assertions.assertNotNull(retrievedPlans, "Improvement Plans should not be null");
        Assertions.assertEquals(2, retrievedPlans.size(), "The size of Improvement Plans list should be 2");
        Assertions.assertEquals("Improvement Plan 1", retrievedPlans.get(0).getContent(), "Content of the first plan mismatch");
        Assertions.assertEquals("Improvement Plan 2", retrievedPlans.get(1).getContent(), "Content of the second plan mismatch");
    }

    @Test
    public void testAddImprovementPlan() {
        // Create a sample ImprovementPlan
        ImprovementPlan improvementPlan = new ImprovementPlan();
        improvementPlan.setProjectId("456");
        improvementPlan.setContent("New Improvement Plan");

        // Mock the repository behavior
        when(improvementPlanRepository.save(any(ImprovementPlan.class))).thenReturn(improvementPlan);

        // Call the method under test
        ImprovementPlan savedPlan = improvementPlanService.addImprovementPlan("456", "New Improvement Plan");

        // Assertions
        Assertions.assertNotNull(savedPlan, "Saved Improvement Plan should not be null");
        Assertions.assertEquals("456", savedPlan.getProjectId(), "Project ID mismatch");
        Assertions.assertEquals("New Improvement Plan", savedPlan.getContent(), "Content mismatch");
    }
}
