package com.example.backdsdm.testIntegration.service;

import com.example.backdsdm.Repositories.ReleaseRepository;
import com.example.backdsdm.entities.Release;
import com.example.backdsdm.services.ReleaseServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@SpringBootTest
@Rollback
public class ReleaseServiceImplIntegrationTest {

    @Autowired
    private ReleaseRepository releaseRepository;

    @Autowired
    private ReleaseServiceImpl releaseService;

    @BeforeEach
    public void setUp() {
        releaseRepository.deleteAll();
    }

    @Test
    public void testGetReleasesByDeploymentPlanId() {
        // Create and save sample releases
        Release release1 = new Release();
        release1.setDeploymentPlanId("456");
        release1.setName("Release 1");
        release1.setDetails("Details 1");

        Release release2 = new Release();
        release2.setDeploymentPlanId("456");
        release2.setName("Release 2");
        release2.setDetails("Details 2");

        releaseRepository.save(release1);
        releaseRepository.save(release2);

        // Call the method under test
        List<Release> retrievedReleases = releaseService.getReleasesByDeploymentPlanId("456");

        // Assertions
        Assertions.assertNotNull(retrievedReleases, "Releases should not be null");
        Assertions.assertEquals(2, retrievedReleases.size(), "The size of Releases list should be 2");
        Assertions.assertEquals("Release 1", retrievedReleases.get(0).getName(), "Name of the first release mismatch");
        Assertions.assertEquals("Details 1", retrievedReleases.get(0).getDetails(), "Details of the first release mismatch");
        Assertions.assertEquals("Release 2", retrievedReleases.get(1).getName(), "Name of the second release mismatch");
        Assertions.assertEquals("Details 2", retrievedReleases.get(1).getDetails(), "Details of the second release mismatch");
    }

    @Test
    public void testAddRelease() {
        // Call the method under test
        Release savedRelease = releaseService.addRelease("456", "New Release", "New Details");

        // Assertions
        Assertions.assertNotNull(savedRelease, "Saved Release should not be null");
        Assertions.assertEquals("456", savedRelease.getDeploymentPlanId(), "Deployment Plan ID mismatch");
        Assertions.assertEquals("New Release", savedRelease.getName(), "Name mismatch");
        Assertions.assertEquals("New Details", savedRelease.getDetails(), "Details mismatch");

        // Verify that the release was actually saved in the repository
        Release foundRelease = releaseRepository.findById(savedRelease.getId()).orElse(null);
        Assertions.assertNotNull(foundRelease, "Release should be found in the repository");
        Assertions.assertEquals("New Release", foundRelease.getName(), "Stored Release name mismatch");
        Assertions.assertEquals("New Details", foundRelease.getDetails(), "Stored Release details mismatch");
    }
}
