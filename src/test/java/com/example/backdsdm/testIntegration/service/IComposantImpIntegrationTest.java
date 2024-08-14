package com.example.backdsdm.testIntegration.service;

import com.example.backdsdm.Repositories.ComposantRepository;
import com.example.backdsdm.entities.Composant;
import com.example.backdsdm.services.IComposantImp;
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
public class IComposantImpIntegrationTest {

    @Autowired
    private IComposantImp composantService;

    @Autowired
    private ComposantRepository composantRepository;

    @BeforeEach
    public void setUp() {
        composantRepository.deleteAll(); // Nettoyez les données avant chaque test
    }

    @Test
    public void testAddComposant() {
        // Créez un nouveau composant
        Composant composant = new Composant();
        composant.setNomComposant("Sample Component");
        composant.setTitre("Sample Title");
        composant.setDesc("Sample Description");

        // Appel de la méthode sous test
        Composant savedComposant = composantService.addComposant(composant);

        // Assertions
        Assertions.assertNotNull(savedComposant.getId(), "ID ne doit pas être nul");
        Assertions.assertEquals("Sample Component", savedComposant.getNomComposant(), "Mismatch du nom du composant");
        Assertions.assertEquals("Sample Title", savedComposant.getTitre(), "Mismatch du titre");
        Assertions.assertEquals("Sample Description", savedComposant.getDesc(), "Mismatch de la description");
    }

    @Test
    public void testRetrieveComposants() {
        // Créez une liste de composants
        Composant comp1 = new Composant();
        comp1.setNomComposant("Component 1");

        Composant comp2 = new Composant();
        comp2.setNomComposant("Component 2");

        composantRepository.save(comp1);
        composantRepository.save(comp2);

        // Appel de la méthode sous test
        List<Composant> retrievedComposants = composantService.retrieveComposants();

        // Assertions
        Assertions.assertNotNull(retrievedComposants, "La liste ne doit pas être nulle");
        Assertions.assertEquals(2, retrievedComposants.size(), "Mismatch de taille de liste");
        Assertions.assertEquals("Component 1", retrievedComposants.get(0).getNomComposant(), "Mismatch du premier nom du composant");
        Assertions.assertEquals("Component 2", retrievedComposants.get(1).getNomComposant(), "Mismatch du second nom du composant");
    }

    @Test
    public void testUpdateComposant() {
        // Créez un composant existant
        Composant existingComposant = new Composant();
        existingComposant.setNomComposant("Original Component");

        Composant savedComposant = composantRepository.save(existingComposant);

        // Créez un composant mis à jour
        Composant updatedComposant = new Composant();
        updatedComposant.setNomComposant("Updated Component");
        updatedComposant.setTitre("Updated Title");
        updatedComposant.setDesc("Updated Description");

        // Appel de la méthode sous test
        Composant savedUpdatedComposant = composantService.updateComposant(new ObjectId(savedComposant.getId()), updatedComposant);

        // Assertions
        Assertions.assertEquals("Updated Component", savedUpdatedComposant.getNomComposant(), "Le nom du composant doit être mis à jour");
        Assertions.assertEquals("Updated Title", savedUpdatedComposant.getTitre(), "Le titre doit être mis à jour");
        Assertions.assertEquals("Updated Description", savedUpdatedComposant.getDesc(), "La description doit être mise à jour");
    }

    @Test
    public void testRemoveComposant() {
        // Créez un composant à supprimer
        Composant composant = new Composant();
        composant.setNomComposant("Component to be deleted");

        Composant savedComposant = composantRepository.save(composant);

        // Appel de la méthode sous test
        composantService.removeComposant(new ObjectId(savedComposant.getId()));

        // Vérifiez que le composant a été supprimé
        Optional<Composant> deletedComposant = composantRepository.findById(new ObjectId(savedComposant.getId()));
        Assertions.assertTrue(deletedComposant.isEmpty(), "Le composant doit être supprimé");
    }
}
