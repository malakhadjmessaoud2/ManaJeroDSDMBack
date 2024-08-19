package com.example.backdsdm.testIntegration.service;

import com.example.backdsdm.Repositories.FoundationRepository;
import com.example.backdsdm.entities.Foundation;
import com.example.backdsdm.services.FoundationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test") // Assurez-vous que vous utilisez le profil de test
public class FoundationServiceImplIntegrationTest {

    @Autowired
    private FoundationServiceImpl foundationService;

    @Autowired
    private FoundationRepository foundationRepository;

    @BeforeEach
    public void setUp() {
        foundationRepository.deleteAll(); // Nettoyez les données avant chaque test
    }

    @Test
    public void testAddFoundation() {
        // Créez un nouveau Foundation
        Foundation foundation = new Foundation();
        foundation.setId("foundation123");
        foundation.setProject("project123");
        foundation.setProjectVision("Vision Test");

        // Appel de la méthode sous test
        Foundation savedFoundation = foundationService.addFoundation(foundation);

        // Assertions
        Assertions.assertNotNull(savedFoundation, "La fondation enregistrée ne doit pas être nulle");
        Assertions.assertEquals("foundation123", savedFoundation.getId(), "ID mismatch");
        Assertions.assertEquals("project123", savedFoundation.getProject(), "Project ID mismatch");
        Assertions.assertEquals("Vision Test", savedFoundation.getProjectVision(), "Project Vision mismatch");
    }

    @Test
    public void testGetFoundationByProject() {
        // Créez un nouveau Foundation
        Foundation foundation = new Foundation();
        foundation.setProject("project123");
        foundation.setProjectVision("Vision Test");

        // Sauvegardez la fondation dans le dépôt
        foundationRepository.save(foundation);

        // Appel de la méthode sous test
        Foundation foundFoundation = foundationService.getFoundationByProject("project123");

        // Assertions
        Assertions.assertNotNull(foundFoundation, "La fondation trouvée ne doit pas être nulle");
        Assertions.assertEquals("project123", foundFoundation.getProject(), "Project ID mismatch");
    }

    @Test
    public void testUpdateFoundation() {
        // Créez un nouveau Foundation
        Foundation foundation = new Foundation();
        foundation.setId("foundation123");
        foundation.setProject("project123");
        foundation.setProjectVision("Original Vision");

        // Sauvegardez la fondation dans le dépôt
        foundationRepository.save(foundation);

        // Appel de la méthode sous test
        Foundation updatedFoundation = foundationService.updateFoundation(
                "project123", "foundation123",
                "Updated Vision", "New Needs", "New Charter", "New Requirements", "user123"
        );

        // Assertions
        Assertions.assertEquals("Updated Vision", updatedFoundation.getProjectVision(), "Project Vision should be updated");
        Assertions.assertEquals("New Needs", updatedFoundation.getUserNeeds(), "User Needs should be updated");
        Assertions.assertEquals("New Charter", updatedFoundation.getProjectCharter(), "Project Charter should be updated");
        Assertions.assertEquals("New Requirements", updatedFoundation.getRequirements(), "Requirements should be updated");
        Assertions.assertEquals("user123", updatedFoundation.getIdUser(), "User ID should be updated");
    }
}
