package com.example.backdsdm.service;

import com.example.backdsdm.Repositories.ReleaseRepository;
import com.example.backdsdm.entities.Release;
import com.example.backdsdm.services.ReleaseServiceImpl;
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
public class ReleaseServiceImplTest {

    @Mock
    private ReleaseRepository releaseRepository;

    @InjectMocks
    private ReleaseServiceImpl releaseService;

    @Test
    public void testGetReleasesByDeploymentPlanId() {
        // Create a sample list of Releases
        Release release1 = new Release();
        release1.setDeploymentPlanId("456");
        release1.setName("Release 1");
        release1.setDetails("Details 1");

        Release release2 = new Release();
        release2.setDeploymentPlanId("456");
        release2.setName("Release 2");
        release2.setDetails("Details 2");

        List<Release> releases = Arrays.asList(release1, release2);

        // Mock the repository behavior
        when(releaseRepository.findByDeploymentPlanId("456")).thenReturn(releases);

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
        // Create a sample Release
        Release release = new Release();
        release.setDeploymentPlanId("456");
        release.setName("New Release");
        release.setDetails("New Details");

        // Mock the repository behavior
        when(releaseRepository.save(any(Release.class))).thenReturn(release);

        // Call the method under test
        Release savedRelease = releaseService.addRelease("456", "New Release", "New Details");

        // Assertions
        Assertions.assertNotNull(savedRelease, "Saved Release should not be null");
        Assertions.assertEquals("456", savedRelease.getDeploymentPlanId(), "Deployment Plan ID mismatch");
        Assertions.assertEquals("New Release", savedRelease.getName(), "Name mismatch");
        Assertions.assertEquals("New Details", savedRelease.getDetails(), "Details mismatch");
    }
}
