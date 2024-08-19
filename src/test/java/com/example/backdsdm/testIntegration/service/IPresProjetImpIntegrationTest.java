package com.example.backdsdm.testIntegration.service;

import com.example.backdsdm.Repositories.PresProjetRepository;
import com.example.backdsdm.entities.PresProjet;
import com.example.backdsdm.services.IPresProjetImp;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test") // Utiliser le profil de test
public class IPresProjetImpIntegrationTest {

    @Autowired
    private PresProjetRepository presProjetRepository;

    @Autowired
    private IPresProjetImp presProjetService;

    @BeforeEach
    public void setUp() {
        // Supprimer toutes les données avant chaque test pour garantir un état propre
        presProjetRepository.deleteAll();
    }

    @Test
    public void testAddDsdm() {
        // Créer un objet PresProjet
        PresProjet presProjet = new PresProjet();
        presProjet.setId(new ObjectId().toString());  // Générer un ID manuellement
        presProjet.setContext("Sample Context");

        // Appel de la méthode sous test
        PresProjet savedPresProjet = presProjetService.addDsdm(presProjet);

        // Assertions
        Assertions.assertNotNull(savedPresProjet.getId(), "L'ID ne doit pas être nul");
        Assertions.assertEquals("Sample Context", savedPresProjet.getContext(), "Mismatch du contexte");
    }

    @Test
    public void testUpdateDsdm() {
        // Créer et sauvegarder un objet PresProjet
        PresProjet presProjet = new PresProjet();
        presProjet.setId("123");
        presProjet.setIdproject("456");
        presProjet.setContext("Original Context");
        presProjetRepository.save(presProjet);

        // Appel de la méthode sous test
        PresProjet updatedPresProjet = presProjetService.updateDsdm("456", "123", "Updated Context", "High", "In Progress", "2024-01-01", "2024-12-31", "user123");

        // Assertions
        Assertions.assertEquals("Updated Context", updatedPresProjet.getContext(), "Le contexte devrait être mis à jour");
        Assertions.assertEquals("High", updatedPresProjet.getPriorisation(), "La priorisation devrait être mise à jour");
        Assertions.assertEquals("In Progress", updatedPresProjet.getStatus(), "Le statut devrait être mis à jour");
        Assertions.assertEquals(LocalDate.parse("2024-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")), updatedPresProjet.getStartDate(), "Mismatch de la date de début");
        Assertions.assertEquals(LocalDate.parse("2024-12-31", DateTimeFormatter.ofPattern("yyyy-MM-dd")), updatedPresProjet.getEndDate(), "Mismatch de la date de fin");
        Assertions.assertEquals("user123", updatedPresProjet.getId_user(), "Mismatch de l'ID utilisateur");
    }

    @Test
    public void testGetDsdmByProjectId() {
        // Créer et sauvegarder un objet PresProjet
        PresProjet presProjet = new PresProjet();
        presProjet.setIdproject("456");
        presProjetRepository.save(presProjet);

        // Appel de la méthode sous test
        PresProjet foundPresProjet = presProjetService.getDsdmByProjectId("456");

        // Assertions
        Assertions.assertNotNull(foundPresProjet, "Le PresProjet ne doit pas être nul");
        Assertions.assertEquals("456", foundPresProjet.getIdproject(), "Mismatch de l'ID du projet");
    }
}
