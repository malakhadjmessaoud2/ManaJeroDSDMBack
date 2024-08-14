package com.example.backdsdm.service;

import com.example.backdsdm.Repositories.FeasibilityRepository;
import com.example.backdsdm.entities.Feasibility;
import com.example.backdsdm.services.IFeasibilityServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IFeasibilityServiceImpTest {

    @Mock
    private FeasibilityRepository feasibilityRepository;

    @InjectMocks
    private IFeasibilityServiceImp feasibilityService;

    @Test
    public void testAddFeasibility() {
        // Create a sample Feasibility
        Feasibility feasibility = new Feasibility();
        feasibility.setId("123");
        feasibility.setIdproject("456");
        feasibility.setTechnicalFeasibility("Technical Feasibility");
        feasibility.setCommercialFeasibility("Commercial Feasibility");
        feasibility.setMvp("MVP");
        feasibility.setReleaseBoard("Release Board");
        feasibility.setViability("Viability");
        feasibility.setId_user("user123");

        // Mock the repository behavior
        when(feasibilityRepository.save(any(Feasibility.class))).thenReturn(feasibility);

        // Call the method under test
        Feasibility savedFeasibility = feasibilityService.addFeasibility(feasibility);

        // Assertions
        Assertions.assertNotNull(savedFeasibility.getId(), "ID should not be null");
        Assertions.assertEquals("123", savedFeasibility.getId(), "ID mismatch");
        Assertions.assertEquals("456", savedFeasibility.getIdproject(), "Project ID mismatch");
        Assertions.assertEquals("Technical Feasibility", savedFeasibility.getTechnicalFeasibility(), "Technical Feasibility mismatch");
        Assertions.assertEquals("Commercial Feasibility", savedFeasibility.getCommercialFeasibility(), "Commercial Feasibility mismatch");
        Assertions.assertEquals("MVP", savedFeasibility.getMvp(), "MVP mismatch");
        Assertions.assertEquals("Release Board", savedFeasibility.getReleaseBoard(), "Release Board mismatch");
        Assertions.assertEquals("Viability", savedFeasibility.getViability(), "Viability mismatch");
        Assertions.assertEquals("user123", savedFeasibility.getId_user(), "User ID mismatch");
    }

    @Test
    public void testGetFeasibilityByProjectId() {
        // Create a sample Feasibility
        Feasibility feasibility = new Feasibility();
        feasibility.setIdproject("456");

        // Mock the repository behavior
        when(feasibilityRepository.findByIdproject("456")).thenReturn(feasibility);

        // Call the method under test
        Feasibility foundFeasibility = feasibilityService.getFeasibilityByProjectId("456");

        // Assertions
        Assertions.assertNotNull(foundFeasibility, "Feasibility should not be null");
        Assertions.assertEquals("456", foundFeasibility.getIdproject(), "Project ID mismatch");
    }

    @Test
    public void testUpdateFeasibility() {
        // Create a sample Feasibility
        Feasibility existingFeasibility = new Feasibility();
        existingFeasibility.setId("123");
        existingFeasibility.setIdproject("456");
        existingFeasibility.setTechnicalFeasibility("Old Technical Feasibility");

        Feasibility updatedFeasibility = new Feasibility();
        updatedFeasibility.setTechnicalFeasibility("Updated Technical Feasibility");
        updatedFeasibility.setCommercialFeasibility("Updated Commercial Feasibility");
        updatedFeasibility.setMvp("Updated MVP");
        updatedFeasibility.setReleaseBoard("Updated Release Board");
        updatedFeasibility.setViability("Updated Viability");
        updatedFeasibility.setId_user("user456");

        // Mock the repository behavior
        when(feasibilityRepository.findByIdAndIdproject("123", "456")).thenReturn(Optional.of(existingFeasibility));
        when(feasibilityRepository.save(any(Feasibility.class))).thenReturn(updatedFeasibility);

        // Call the method under test
        Feasibility savedUpdatedFeasibility = feasibilityService.updateFeasibility("456", "123", "Updated Technical Feasibility", "Updated Commercial Feasibility", "Updated MVP", "Updated Release Board", "Updated Viability", "user456");

        // Assertions
        Assertions.assertEquals("Updated Technical Feasibility", savedUpdatedFeasibility.getTechnicalFeasibility(), "Technical Feasibility should be updated");
        Assertions.assertEquals("Updated Commercial Feasibility", savedUpdatedFeasibility.getCommercialFeasibility(), "Commercial Feasibility should be updated");
        Assertions.assertEquals("Updated MVP", savedUpdatedFeasibility.getMvp(), "MVP should be updated");
        Assertions.assertEquals("Updated Release Board", savedUpdatedFeasibility.getReleaseBoard(), "Release Board should be updated");
        Assertions.assertEquals("Updated Viability", savedUpdatedFeasibility.getViability(), "Viability should be updated");
        Assertions.assertEquals("user456", savedUpdatedFeasibility.getId_user(), "User ID should be updated");
    }

    @Test
    public void testUpdateFeasibilityNotFound() {
        // Mock the repository behavior for a non-existing feasibility
        when(feasibilityRepository.findByIdAndIdproject(anyString(), anyString())).thenReturn(Optional.empty());

        // Call the method under test and assert that it throws an exception
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            feasibilityService.updateFeasibility("456", "123", "Technical Feasibility", "Commercial Feasibility", "MVP", "Release Board", "Viability", "user123");
        }, "Feasibility not found");
    }
}
