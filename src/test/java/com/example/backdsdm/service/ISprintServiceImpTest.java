package com.example.backdsdm.service;

import com.example.backdsdm.Repositories.SprintRepository;
import com.example.backdsdm.entities.Sprint;
import com.example.backdsdm.services.ISprintServiceImp;
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
public class ISprintServiceImpTest {

    @Mock
    private SprintRepository sprintRepository;

    @InjectMocks
    private ISprintServiceImp sprintService;

    @Test
    public void testGetSprintsByProjectId() {
        // Create a sample list of Sprints
        Sprint sprint1 = new Sprint();
        sprint1.setProjectId("456");
        sprint1.setName("Sprint 1");

        Sprint sprint2 = new Sprint();
        sprint2.setProjectId("456");
        sprint2.setName("Sprint 2");

        List<Sprint> sprints = Arrays.asList(sprint1, sprint2);

        // Mock the repository behavior
        when(sprintRepository.findByProjectId("456")).thenReturn(sprints);

        // Call the method under test
        List<Sprint> retrievedSprints = sprintService.getSprintsByProjectId("456");

        // Assertions
        Assertions.assertNotNull(retrievedSprints, "Sprints should not be null");
        Assertions.assertEquals(2, retrievedSprints.size(), "The size of Sprints list should be 2");
        Assertions.assertEquals("Sprint 1", retrievedSprints.get(0).getName(), "Name of the first sprint mismatch");
        Assertions.assertEquals("Sprint 2", retrievedSprints.get(1).getName(), "Name of the second sprint mismatch");
    }

    @Test
    public void testAddSprint() {
        // Create a sample Sprint
        Sprint sprint = new Sprint();
        sprint.setProjectId("456");
        sprint.setName("New Sprint");

        // Mock the repository behavior
        when(sprintRepository.save(any(Sprint.class))).thenReturn(sprint);

        // Call the method under test
        Sprint savedSprint = sprintService.addSprint("456", "New Sprint");

        // Assertions
        Assertions.assertNotNull(savedSprint, "Saved Sprint should not be null");
        Assertions.assertEquals("456", savedSprint.getProjectId(), "Project ID mismatch");
        Assertions.assertEquals("New Sprint", savedSprint.getName(), "Name mismatch");
    }
}
