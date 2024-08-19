package com.example.backdsdm.testIntegration.service;

import com.example.backdsdm.Repositories.AdvantageRepository;
import com.example.backdsdm.entities.Advantage;
import com.example.backdsdm.services.IAdvantageImp;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test") // Assurez-vous d'utiliser le profil de test
public class IAdvantageImpIntegrationTest {

    @Autowired
    private IAdvantageImp advantageService;

    @Autowired
    private AdvantageRepository advantageRepository;

    @BeforeEach
    public void setUp() {
        advantageRepository.deleteAll(); // Nettoyez les données avant chaque test
    }

    @Test
    public void testAddAdv() {
        // Créez un nouvel avantage
        Advantage advantage = new Advantage();
        advantage.setTitre("Sample Title");
        advantage.setDesc("Sample Description");

        // Appel de la méthode sous test
        Advantage savedAdvantage = advantageService.addAdv(advantage);

        // Assertions
        Assertions.assertNotNull(savedAdvantage.getId(), "ID ne doit pas être nul");
        Assertions.assertEquals("Sample Title", savedAdvantage.getTitre(), "Mismatch de titre");
        Assertions.assertEquals("Sample Description", savedAdvantage.getDesc(), "Mismatch de description");
    }

    @Test
    public void testRetrieveAdvs() {
        // Créez une liste d'avantages
        Advantage adv1 = new Advantage();
        adv1.setTitre("Title 1");

        Advantage adv2 = new Advantage();
        adv2.setTitre("Title 2");

        advantageRepository.save(adv1);
        advantageRepository.save(adv2);

        // Appel de la méthode sous test
        List<Advantage> retrievedAdvs = advantageService.retrieveAdvs();

        // Assertions
        Assertions.assertNotNull(retrievedAdvs, "La liste ne doit pas être nulle");
        Assertions.assertEquals(2, retrievedAdvs.size(), "Mismatch de taille de liste");
        Assertions.assertEquals("Title 1", retrievedAdvs.get(0).getTitre(), "Mismatch du premier titre");
        Assertions.assertEquals("Title 2", retrievedAdvs.get(1).getTitre(), "Mismatch du second titre");
    }

    @Test
    public void testUpdateAdv() {
        // Créez un avantage existant
        Advantage existingAdv = new Advantage();
        existingAdv.setTitre("Original Title");

        Advantage savedAdv = advantageRepository.save(existingAdv);

        // Créez un avantage mis à jour
        Advantage updatedAdv = new Advantage();
        updatedAdv.setTitre("Updated Title");
        updatedAdv.setDesc("Updated Description");

        // Appel de la méthode sous test
        Advantage savedUpdatedAdv = advantageService.updateAdv(new ObjectId(savedAdv.getId()), updatedAdv);

        // Assertions
        Assertions.assertEquals("Updated Title", savedUpdatedAdv.getTitre(), "Le titre doit être mis à jour");
        Assertions.assertEquals("Updated Description", savedUpdatedAdv.getDesc(), "La description doit être mise à jour");
    }

    @Test
    public void testRemoveAdv() {
        // Créez un avantage à supprimer
        Advantage advantage = new Advantage();
        advantage.setTitre("Title to be deleted");

        Advantage savedAdv = advantageRepository.save(advantage);

        // Appel de la méthode sous test
        advantageService.removeAdv(new ObjectId(savedAdv.getId()));

        // Vérifiez que l'avantage a été supprimé
        Optional<Advantage> deletedAdv = advantageRepository.findById(new ObjectId(savedAdv.getId()));
        Assertions.assertTrue(deletedAdv.isEmpty(), "L'avantage doit être supprimé");
    }
}
