package com.example.backdsdm.testIntegration.service;

import com.example.backdsdm.Repositories.IterationRepository;
import com.example.backdsdm.entities.Iteration;
import com.example.backdsdm.services.IterationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Rollback
public class IterationServiceImplIntegrationTest {

    @Autowired
    private IterationRepository iterationRepository;

    @Autowired
    private IterationServiceImpl iterationService;

    @BeforeEach
    public void setUp() {
        iterationRepository.deleteAll();
    }

    @Test
    public void testGetIterationsBySprintId() {
        // Create and save sample iterations
        Iteration iteration1 = new Iteration();
        iteration1.setSprintId("123");
        iteration1.setFeature("Feature 1");
        iteration1.setDeliverables("Deliverable 1");

        Iteration iteration2 = new Iteration();
        iteration2.setSprintId("123");
        iteration2.setFeature("Feature 2");
        iteration2.setDeliverables("Deliverable 2");

        iterationRepository.save(iteration1);
        iterationRepository.save(iteration2);

        // Call the method under test
        List<Iteration> retrievedIterations = iterationService.getIterationsBySprintId("123");

        // Assertions
        Assertions.assertNotNull(retrievedIterations, "Iterations should not be null");
        Assertions.assertEquals(2, retrievedIterations.size(), "The size of Iterations list should be 2");
        Assertions.assertEquals("Feature 1", retrievedIterations.get(0).getFeature(), "Feature of the first iteration mismatch");
        Assertions.assertEquals("Deliverable 1", retrievedIterations.get(0).getDeliverables(), "Deliverables of the first iteration mismatch");
        Assertions.assertEquals("Feature 2", retrievedIterations.get(1).getFeature(), "Feature of the second iteration mismatch");
        Assertions.assertEquals("Deliverable 2", retrievedIterations.get(1).getDeliverables(), "Deliverables of the second iteration mismatch");
    }

    @Test
    public void testAddIteration() {
        // Call the method under test
        Iteration savedIteration = iterationService.addIteration("123", "New Feature", "New Deliverable");

        // Assertions
        Assertions.assertNotNull(savedIteration, "Saved Iteration should not be null");
        Assertions.assertEquals("123", savedIteration.getSprintId(), "Sprint ID mismatch");
        Assertions.assertEquals("New Feature", savedIteration.getFeature(), "Feature mismatch");
        Assertions.assertEquals("New Deliverable", savedIteration.getDeliverables(), "Deliverables mismatch");

        // Verify that the iteration was actually saved in the repository
        Iteration foundIteration = iterationRepository.findById(savedIteration.getId()).orElse(null);
        Assertions.assertNotNull(foundIteration, "Iteration should be found in the repository");
        Assertions.assertEquals("New Feature", foundIteration.getFeature(), "Stored Iteration feature mismatch");
        Assertions.assertEquals("New Deliverable", foundIteration.getDeliverables(), "Stored Iteration deliverables mismatch");
    }
}
