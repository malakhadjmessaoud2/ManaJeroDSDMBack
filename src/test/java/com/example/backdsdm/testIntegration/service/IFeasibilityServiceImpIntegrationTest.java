package com.example.backdsdm.testIntegration.service;

import com.example.backdsdm.Repositories.FeasibilityRepository;
import com.example.backdsdm.entities.Feasibility;
import com.example.backdsdm.services.IFeasibilityServiceImp;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test") // Utiliser le profil de test
public class IFeasibilityServiceImpIntegrationTest {

    @Autowired
    private FeasibilityRepository feasibilityRepository;

    @Autowired
    private IFeasibilityServiceImp feasibilityService;

    @BeforeEach
    public void setUp() {
        // Supprimer toutes les données avant chaque test pour garantir un état propre
        feasibilityRepository.deleteAll();
    }

    @Test
    public void testAddFeasibility() {
        // Créer un nouvel objet Feasibility
        Feasibility feasibility = new Feasibility();
        feasibility.setIdproject("456");
        feasibility.setTechnicalFeasibility("Technical Feasibility");
        feasibility.setCommercialFeasibility("Commercial Feasibility");
        feasibility.setMvp("MVP");
        feasibility.setReleaseBoard("Release Board");
        feasibility.setViability("Viability");
        feasibility.setId_user("user123");

        // Appel de la méthode sous test
        Feasibility savedFeasibility = feasibilityService.addFeasibility(feasibility);

        // Assertions
        Assertions.assertNotNull(savedFeasibility.getId(), "L'ID ne doit pas être nul");
        Assertions.assertEquals("456", savedFeasibility.getIdproject(), "Mismatch de l'ID du projet");
        Assertions.assertEquals("Technical Feasibility", savedFeasibility.getTechnicalFeasibility(), "Mismatch de faisabilité technique");
        Assertions.assertEquals("Commercial Feasibility", savedFeasibility.getCommercialFeasibility(), "Mismatch de faisabilité commerciale");
        Assertions.assertEquals("MVP", savedFeasibility.getMvp(), "Mismatch de MVP");
        Assertions.assertEquals("Release Board", savedFeasibility.getReleaseBoard(), "Mismatch de Release Board");
        Assertions.assertEquals("Viability", savedFeasibility.getViability(), "Mismatch de viabilité");
        Assertions.assertEquals("user123", savedFeasibility.getId_user(), "Mismatch de l'ID de l'utilisateur");
    }

    @Test
    public void testGetFeasibilityByProjectId() {
        // Créer et sauvegarder un objet Feasibility
        Feasibility feasibility = new Feasibility();
        feasibility.setIdproject("456");
        feasibilityRepository.save(feasibility);

        // Appel de la méthode sous test
        Feasibility foundFeasibility = feasibilityService.getFeasibilityByProjectId("456");

        // Assertions
        Assertions.assertNotNull(foundFeasibility, "La faisabilité ne doit pas être nulle");
        Assertions.assertEquals("456", foundFeasibility.getIdproject(), "Mismatch de l'ID du projet");
    }

    @Test
    public void testUpdateFeasibility() {
        // Créer et sauvegarder une faisabilité existante
        Feasibility existingFeasibility = new Feasibility();
        existingFeasibility.setId(new ObjectId().toHexString());
        existingFeasibility.setIdproject("456");
        existingFeasibility.setTechnicalFeasibility("Old Technical Feasibility");
        feasibilityRepository.save(existingFeasibility);

        // Mettre à jour la faisabilité
        Feasibility updatedFeasibility = feasibilityService.updateFeasibility(
                existingFeasibility.getIdproject(),
                existingFeasibility.getId(),
                "Updated Technical Feasibility",
                "Updated Commercial Feasibility",
                "Updated MVP",
                "Updated Release Board",
                "Updated Viability",
                "user456"
        );

        // Assertions
        Assertions.assertEquals("Updated Technical Feasibility", updatedFeasibility.getTechnicalFeasibility(), "La faisabilité technique doit être mise à jour");
        Assertions.assertEquals("Updated Commercial Feasibility", updatedFeasibility.getCommercialFeasibility(), "La faisabilité commerciale doit être mise à jour");
        Assertions.assertEquals("Updated MVP", updatedFeasibility.getMvp(), "Le MVP doit être mis à jour");
        Assertions.assertEquals("Updated Release Board", updatedFeasibility.getReleaseBoard(), "Le Release Board doit être mis à jour");
        Assertions.assertEquals("Updated Viability", updatedFeasibility.getViability(), "La viabilité doit être mise à jour");
        Assertions.assertEquals("user456", updatedFeasibility.getId_user(), "L'ID utilisateur doit être mis à jour");
    }

    @Test
    public void testUpdateFeasibilityNotFound() {
        // Appel de la méthode sous test avec un ID de projet et un ID de faisabilité qui n'existent pas
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            feasibilityService.updateFeasibility(
                    "nonexistent_project_id",
                    "nonexistent_id",
                    "Technical Feasibility",
                    "Commercial Feasibility",
                    "MVP",
                    "Release Board",
                    "Viability",
                    "user123"
            );
        }, "Faisabilité non trouvée");
    }
}
