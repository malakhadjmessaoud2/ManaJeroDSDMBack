package com.example.backdsdm.service;

import com.example.backdsdm.Repositories.IterationRepository;
import com.example.backdsdm.Repositories.SprintRepository;
import com.example.backdsdm.entities.Iteration;
import com.example.backdsdm.services.IterationServiceImpl;
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
public class IterationServiceImplTest {

    @Mock
    private IterationRepository iterationRepository;

    @Mock
    private SprintRepository sprintRepository;

    @InjectMocks
    private IterationServiceImpl iterationService;

    @Test
    public void testGetIterationsBySprintId() {
        // Create a sample list of Iterations
        Iteration iteration1 = new Iteration();
        iteration1.setSprintId("123");
        iteration1.setFeature("Feature 1");
        iteration1.setDeliverables("Deliverable 1");

        Iteration iteration2 = new Iteration();
        iteration2.setSprintId("123");
        iteration2.setFeature("Feature 2");
        iteration2.setDeliverables("Deliverable 2");

        List<Iteration> iterations = Arrays.asList(iteration1, iteration2);

        // Mock the repository behavior
        when(iterationRepository.findBySprintId("123")).thenReturn(iterations);

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
        // Create a sample Iteration
        Iteration iteration = new Iteration();
        iteration.setSprintId("123");
        iteration.setFeature("New Feature");
        iteration.setDeliverables("New Deliverable");

        // Mock the repository behavior
        when(iterationRepository.save(any(Iteration.class))).thenReturn(iteration);

        // Call the method under test
        Iteration savedIteration = iterationService.addIteration("123", "New Feature", "New Deliverable");

        // Assertions
        Assertions.assertNotNull(savedIteration, "Saved Iteration should not be null");
        Assertions.assertEquals("123", savedIteration.getSprintId(), "Sprint ID mismatch");
        Assertions.assertEquals("New Feature", savedIteration.getFeature(), "Feature mismatch");
        Assertions.assertEquals("New Deliverable", savedIteration.getDeliverables(), "Deliverables mismatch");
    }
}
