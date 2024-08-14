package com.example.backdsdm.service;

import com.example.backdsdm.Repositories.FoundationRepository;
import com.example.backdsdm.entities.Foundation;
import com.example.backdsdm.services.FoundationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FoundationServiceImplTest {

    @Mock
    private FoundationRepository foundationRepository;

    @InjectMocks
    private FoundationServiceImpl foundationService;

    @Test
    public void testAddFoundation() {
        // Create a sample Foundation
        Foundation foundation = new Foundation();
        foundation.setId("foundation123");
        foundation.setProject("project123");
        foundation.setProjectVision("Vision Test");

        // Mock the repository behavior
        when(foundationRepository.save(any(Foundation.class))).thenReturn(foundation);

        // Call the method under test
        Foundation savedFoundation = foundationService.addFoundation(foundation);

        // Assertions
        Assertions.assertNotNull(savedFoundation.getId(), "ID should not be null");
        Assertions.assertEquals("foundation123", savedFoundation.getId(), "ID mismatch");
        Assertions.assertEquals("project123", savedFoundation.getProject(), "Project ID mismatch");
        Assertions.assertEquals("Vision Test", savedFoundation.getProjectVision(), "Project Vision mismatch");
    }

    @Test
    public void testGetFoundationByProject() {
        // Create a sample Foundation
        Foundation foundation = new Foundation();
        foundation.setProject("project123");

        // Mock the repository behavior
        when(foundationRepository.findByProject("project123")).thenReturn(foundation);

        // Call the method under test
        Foundation foundFoundation = foundationService.getFoundationByProject("project123");

        // Assertions
        Assertions.assertNotNull(foundFoundation, "Foundation should not be null");
        Assertions.assertEquals("project123", foundFoundation.getProject(), "Project ID mismatch");
    }

    @Test
    public void testUpdateFoundation() {
        // Create a sample Foundation
        Foundation foundation = new Foundation();
        foundation.setId("foundation123");
        foundation.setProject("project123");
        foundation.setProjectVision("Original Vision");

        // Mock the repository behavior
        when(foundationRepository.findByIdAndProject("foundation123", "project123")).thenReturn(Optional.of(foundation));
        when(foundationRepository.save(any(Foundation.class))).thenReturn(foundation);

        // Call the method under test
        Foundation updatedFoundation = foundationService.updateFoundation("project123", "foundation123", "Updated Vision", "New Needs", "New Charter", "New Requirements", "user123");

        // Assertions
        Assertions.assertEquals("Updated Vision", updatedFoundation.getProjectVision(), "Project Vision should be updated");
        Assertions.assertEquals("New Needs", updatedFoundation.getUserNeeds(), "User Needs should be updated");
        Assertions.assertEquals("New Charter", updatedFoundation.getProjectCharter(), "Project Charter should be updated");
        Assertions.assertEquals("New Requirements", updatedFoundation.getRequirements(), "Requirements should be updated");
        Assertions.assertEquals("user123", updatedFoundation.getIdUser(), "User ID should be updated");
    }
}
