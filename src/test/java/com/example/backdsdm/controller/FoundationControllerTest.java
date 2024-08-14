package com.example.backdsdm.controller;

import com.example.backdsdm.Controller.FoundationController;
import com.example.backdsdm.entities.Foundation;
import com.example.backdsdm.services.IFoundationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FoundationControllerTest {

    @Mock
    private IFoundationService foundationService;

    @InjectMocks
    private FoundationController foundationController;

    @BeforeEach
    public void setUp() {
        // Initialization logic if needed
    }

    @Test
    public void testAddFoundation() {
        // Create a sample Foundation
        Foundation foundation = new Foundation();
        foundation.setProject("project123");
        foundation.setProjectVision("Vision Test");
        foundation.setUserNeeds("User Needs");
        foundation.setProjectCharter("Project Charter");
        foundation.setRequirements("Requirements");
        foundation.setIdUser("user123");

        // Mock the service behavior
        when(foundationService.addFoundation(any(Foundation.class))).thenReturn(foundation);

        // Call the controller method
        ResponseEntity<Foundation> response = foundationController.addFoundation(
                "project123",
                "Vision Test",
                "User Needs",
                "Project Charter",
                "Requirements",
                "user123"
        );

        // Assertions
        assertEquals(200, response.getStatusCodeValue(), "Response status should be OK");
        assertNotNull(response.getBody(), "Response body should not be null");
        assertEquals("project123", response.getBody().getProject(), "Project ID mismatch");
        assertEquals("Vision Test", response.getBody().getProjectVision(), "Project Vision mismatch");
        assertEquals("User Needs", response.getBody().getUserNeeds(), "User Needs mismatch");
        assertEquals("Project Charter", response.getBody().getProjectCharter(), "Project Charter mismatch");
        assertEquals("Requirements", response.getBody().getRequirements(), "Requirements mismatch");
        assertEquals("user123", response.getBody().getIdUser(), "User ID mismatch");
    }

    @Test
    public void testGetFoundationByProject() {
        // Create a sample Foundation
        Foundation foundation = new Foundation();
        foundation.setProject("project123");

        // Mock the service behavior
        when(foundationService.getFoundationByProject("project123")).thenReturn(foundation);

        // Call the controller method
        ResponseEntity<Foundation> response = foundationController.getFoundationByProject("project123");

        // Assertions
        assertEquals(200, response.getStatusCodeValue(), "Response status should be OK");
        assertNotNull(response.getBody(), "Response body should not be null");
        assertEquals("project123", response.getBody().getProject(), "Project ID mismatch");
    }

    @Test
    public void testGetFoundationByProject_NotFound() {
        // Mock the service behavior
        when(foundationService.getFoundationByProject("nonexistentProject")).thenReturn(null);

        // Call the controller method
        ResponseEntity<Foundation> response = foundationController.getFoundationByProject("nonexistentProject");

        // Assertions
        assertEquals(404, response.getStatusCodeValue(), "Response status should be Not Found");
        assertNull(response.getBody(), "Response body should be null");
    }

    @Test
    public void testUpdateFoundation() {
        // Create a sample Foundation
        Foundation foundation = new Foundation();
        foundation.setProject("project123");
        foundation.setId("foundation123");
        foundation.setProjectVision("Updated Vision");
        foundation.setUserNeeds("New Needs");
        foundation.setProjectCharter("New Charter");
        foundation.setRequirements("New Requirements");
        foundation.setIdUser("user123");

        // Mock the service behavior
        when(foundationService.updateFoundation(
                anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString()
        )).thenReturn(foundation);

        // Call the controller method
        ResponseEntity<Foundation> response = foundationController.updateFoundation(
                "project123",
                "foundation123",
                "Updated Vision",
                "New Needs",
                "New Charter",
                "New Requirements",
                "user123"
        );

        // Assertions
        assertEquals(200, response.getStatusCodeValue(), "Response status should be OK");
        assertNotNull(response.getBody(), "Response body should not be null");
        assertEquals("Updated Vision", response.getBody().getProjectVision(), "Project Vision mismatch");
        assertEquals("New Needs", response.getBody().getUserNeeds(), "User Needs mismatch");
        assertEquals("New Charter", response.getBody().getProjectCharter(), "Project Charter mismatch");
        assertEquals("New Requirements", response.getBody().getRequirements(), "Requirements mismatch");
        assertEquals("user123", response.getBody().getIdUser(), "User ID mismatch");
    }
}