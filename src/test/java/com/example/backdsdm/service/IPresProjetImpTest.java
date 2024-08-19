package com.example.backdsdm.service;

import com.example.backdsdm.Repositories.PresProjetRepository;
import com.example.backdsdm.entities.PresProjet;
import com.example.backdsdm.services.IPresProjetImp;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IPresProjetImpTest {

    @Mock
    private PresProjetRepository presProjetRepository;

    @InjectMocks
    private IPresProjetImp presProjetService;

    @Test
    public void testAddDsdm() {
        // Create a sample PresProjet
        PresProjet presProjet = new PresProjet();
        presProjet.setId(new ObjectId().toString());  // Manually set an ID
        presProjet.setContext("Sample Context");

        // Mock the repository behavior
        when(presProjetRepository.save(any(PresProjet.class))).thenReturn(presProjet);

        // Call the method under test
        PresProjet savedPresProjet = presProjetService.addDsdm(presProjet);

        // Assertions
        Assertions.assertNotNull(savedPresProjet.getId(), "ID should not be null");
        Assertions.assertEquals("Sample Context", savedPresProjet.getContext(), "Context mismatch");
    }

    @Test
    public void testUpdateDsdm() {
        // Create a sample PresProjet
        PresProjet presProjet = new PresProjet();
        presProjet.setId("123");
        presProjet.setIdproject("456");
        presProjet.setContext("Original Context");

        // Mock the repository behavior
        when(presProjetRepository.findByIdAndIdproject("123", "456")).thenReturn(Optional.of(presProjet));
        when(presProjetRepository.save(any(PresProjet.class))).thenReturn(presProjet);

        // Call the method under test
        PresProjet updatedPresProjet = presProjetService.updateDsdm("456", "123", "Updated Context", "High", "In Progress", "2024-01-01", "2024-12-31", "user123");

        // Assertions
        Assertions.assertEquals("Updated Context", updatedPresProjet.getContext(), "Context should be updated");
        Assertions.assertEquals("High", updatedPresProjet.getPriorisation(), "Priorisation should be updated");
        Assertions.assertEquals("In Progress", updatedPresProjet.getStatus(), "Status should be updated");
        Assertions.assertEquals(LocalDate.parse("2024-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")), updatedPresProjet.getStartDate(), "Start date mismatch");
        Assertions.assertEquals(LocalDate.parse("2024-12-31", DateTimeFormatter.ofPattern("yyyy-MM-dd")), updatedPresProjet.getEndDate(), "End date mismatch");
        Assertions.assertEquals("user123", updatedPresProjet.getId_user(), "User ID mismatch");
    }

    @Test
    public void testGetDsdmByProjectId() {
        // Create a sample PresProjet
        PresProjet presProjet = new PresProjet();
        presProjet.setIdproject("456");

        // Mock the repository behavior
        when(presProjetRepository.findByIdproject("456")).thenReturn(presProjet);

        // Call the method under test
        PresProjet foundPresProjet = presProjetService.getDsdmByProjectId("456");

        // Assertions
        Assertions.assertNotNull(foundPresProjet, "PresProjet should not be null");
        Assertions.assertEquals("456", foundPresProjet.getIdproject(), "Project ID mismatch");
    }
}
