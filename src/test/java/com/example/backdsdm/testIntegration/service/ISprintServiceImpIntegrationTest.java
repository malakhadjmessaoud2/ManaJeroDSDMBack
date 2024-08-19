package com.example.backdsdm.testIntegration.service;

import com.example.backdsdm.Repositories.SprintRepository;
import com.example.backdsdm.entities.Sprint;
import com.example.backdsdm.services.ISprintServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@SpringBootTest
@Rollback
public class ISprintServiceImpIntegrationTest {

    @Autowired
    private SprintRepository sprintRepository;

    @Autowired
    private ISprintServiceImp sprintService;

    @BeforeEach
    public void setUp() {
        sprintRepository.deleteAll();
    }

    @Test
    public void testGetSprintsByProjectId() {
        // Create and save sample sprints
        Sprint sprint1 = new Sprint();
        sprint1.setProjectId("456");
        sprint1.setName("Sprint 1");

        Sprint sprint2 = new Sprint();
        sprint2.setProjectId("456");
        sprint2.setName("Sprint 2");

        sprintRepository.save(sprint1);
        sprintRepository.save(sprint2);

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
        // Call the method under test
        Sprint savedSprint = sprintService.addSprint("456", "New Sprint");

        // Assertions
        Assertions.assertNotNull(savedSprint, "Saved Sprint should not be null");
        Assertions.assertEquals("456", savedSprint.getProjectId(), "Project ID mismatch");
        Assertions.assertEquals("New Sprint", savedSprint.getName(), "Name mismatch");

        // Verify that the sprint was actually saved in the repository
        Sprint foundSprint = sprintRepository.findById(savedSprint.getId()).orElse(null);
        Assertions.assertNotNull(foundSprint, "Sprint should be found in the repository");
        Assertions.assertEquals("New Sprint", foundSprint.getName(), "Stored Sprint name mismatch");
    }
}
