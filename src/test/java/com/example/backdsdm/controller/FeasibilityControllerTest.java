package com.example.backdsdm.controller;

import com.example.backdsdm.Controller.FeasibilityController;
import com.example.backdsdm.entities.Feasibility;
import com.example.backdsdm.services.IFeasibilityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FeasibilityControllerTest {

    @Mock
    private IFeasibilityService feasibilityService;

    @InjectMocks
    private FeasibilityController feasibilityController;

    @BeforeEach
    public void setUp() {
        // Initialization logic if needed
    }

    @Test
    public void testAddFeasibility() throws IOException {
        // Create a sample Feasibility object
        Feasibility feasibility = new Feasibility();
        feasibility.setIdproject("projectId123");
        feasibility.setTechnicalFeasibility("High");
        feasibility.setCommercialFeasibility("Medium");
        feasibility.setMvp("MVP 1");
        feasibility.setReleaseBoard("Release 1");
        feasibility.setViability("Viable");
        feasibility.setId_user("userId123");

        // Mock the service behavior
        when(feasibilityService.addFeasibility(any(Feasibility.class))).thenReturn(feasibility);

        // Call the controller method
        Feasibility savedFeasibility = feasibilityController.addFeasibility(
                "projectId123",
                "High",
                "Medium",
                "MVP 1",
                "Release 1",
                "Viable",
                "userId123"
        );

        // Assertions
        assertNotNull(savedFeasibility, "Saved Feasibility should not be null");
        assertEquals("projectId123", savedFeasibility.getIdproject(), "Project ID mismatch");
        assertEquals("High", savedFeasibility.getTechnicalFeasibility(), "Technical Feasibility mismatch");
        assertEquals("Medium", savedFeasibility.getCommercialFeasibility(), "Commercial Feasibility mismatch");
        assertEquals("MVP 1", savedFeasibility.getMvp(), "MVP mismatch");
        assertEquals("Release 1", savedFeasibility.getReleaseBoard(), "Release Board mismatch");
        assertEquals("Viable", savedFeasibility.getViability(), "Viability mismatch");
        assertEquals("userId123", savedFeasibility.getId_user(), "User ID mismatch");
    }

    @Test
    public void testUpdateFeasibility() {
        // Create a sample Feasibility object
        Feasibility feasibility = new Feasibility();
        feasibility.setIdproject("projectId123");
        feasibility.setTechnicalFeasibility("Medium");
        feasibility.setCommercialFeasibility("Low");
        feasibility.setMvp("MVP 2");
        feasibility.setReleaseBoard("Release 2");
        feasibility.setViability("Not Viable");
        feasibility.setId_user("userId123");

        // Mock the service behavior with exact arguments
        when(feasibilityService.updateFeasibility(
                "projectId123",
                "feasibilityId123",
                "Medium",
                "Low",
                "MVP 2",
                "Release 2",
                "Not Viable",
                "userId123"
        )).thenReturn(feasibility);

        // Call the controller method
        ResponseEntity<Feasibility> responseEntity = feasibilityController.updateFeasibility(
                "projectId123",
                "feasibilityId123",
                "Medium",
                "Low",
                "MVP 2",
                "Release 2",
                "Not Viable",
                "userId123"
        );

        // Assertions
        assertNotNull(responseEntity, "Response entity should not be null");
        assertEquals(200, responseEntity.getStatusCodeValue(), "HTTP status code should be 200 OK");
        Feasibility updatedFeasibility = responseEntity.getBody();
        assertNotNull(updatedFeasibility, "Updated Feasibility should not be null");
        assertEquals("projectId123", updatedFeasibility.getIdproject(), "Project ID mismatch");
        assertEquals("Medium", updatedFeasibility.getTechnicalFeasibility(), "Technical Feasibility mismatch");
        assertEquals("Low", updatedFeasibility.getCommercialFeasibility(), "Commercial Feasibility mismatch");
        assertEquals("MVP 2", updatedFeasibility.getMvp(), "MVP mismatch");
        assertEquals("Release 2", updatedFeasibility.getReleaseBoard(), "Release Board mismatch");
        assertEquals("Not Viable", updatedFeasibility.getViability(), "Viability mismatch");
        assertEquals("userId123", updatedFeasibility.getId_user(), "User ID mismatch");
    }

    @Test
    public void testGetFeasibilityByProjectId() {
        // Create a sample Feasibility object
        Feasibility feasibility = new Feasibility();
        feasibility.setIdproject("projectId123");
        feasibility.setTechnicalFeasibility("Medium");
        feasibility.setCommercialFeasibility("Low");
        feasibility.setMvp("MVP 2");
        feasibility.setReleaseBoard("Release 2");
        feasibility.setViability("Not Viable");
        feasibility.setId_user("userId123");

        // Mock the service behavior
        when(feasibilityService.getFeasibilityByProjectId("projectId123")).thenReturn(feasibility);

        // Call the controller method
        Feasibility foundFeasibility = feasibilityController.getFeasibilityByProjectId("projectId123");

        // Assertions
        assertNotNull(foundFeasibility, "Feasibility should not be null");
        assertEquals("projectId123", foundFeasibility.getIdproject(), "Project ID mismatch");
        assertEquals("Medium", foundFeasibility.getTechnicalFeasibility(), "Technical Feasibility mismatch");
        assertEquals("Low", foundFeasibility.getCommercialFeasibility(), "Commercial Feasibility mismatch");
        assertEquals("MVP 2", foundFeasibility.getMvp(), "MVP mismatch");
        assertEquals("Release 2", foundFeasibility.getReleaseBoard(), "Release Board mismatch");
        assertEquals("Not Viable", foundFeasibility.getViability(), "Viability mismatch");
        assertEquals("userId123", foundFeasibility.getId_user(), "User ID mismatch");
    }
}
