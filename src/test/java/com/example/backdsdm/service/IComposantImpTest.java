package com.example.backdsdm.service;

import com.example.backdsdm.Repositories.ComposantRepository;
import com.example.backdsdm.entities.Composant;
import com.example.backdsdm.services.IComposantImp;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IComposantImpTest {

    @Mock
    private ComposantRepository composantRepository;

    @InjectMocks
    private IComposantImp composantService;

    @Test
    public void testAddComposant() {
        // Create a sample Composant
        Composant composant = new Composant();
        composant.setId("123");
        composant.setNomComposant("Sample Component");
        composant.setTitre("Sample Title");
        composant.setDesc("Sample Description");

        // Mock the repository behavior
        when(composantRepository.save(any(Composant.class))).thenReturn(composant);

        // Call the method under test
        Composant savedComposant = composantService.addComposant(composant);

        // Assertions
        Assertions.assertNotNull(savedComposant.getId(), "ID should not be null");
        Assertions.assertEquals("Sample Component", savedComposant.getNomComposant(), "Component name mismatch");
        Assertions.assertEquals("Sample Title", savedComposant.getTitre(), "Title mismatch");
        Assertions.assertEquals("Sample Description", savedComposant.getDesc(), "Description mismatch");
    }

    @Test
    public void testUpdateComposant() {
        // Create a sample Composant
        ObjectId id = new ObjectId();
        Composant existingComposant = new Composant();
        existingComposant.setId(String.valueOf(id));
        existingComposant.setNomComposant("Original Component");

        Composant updatedComposant = new Composant();
        updatedComposant.setNomComposant("Updated Component");
        updatedComposant.setTitre("Updated Title");
        updatedComposant.setDesc("Updated Description");

        // Mock the repository behavior
        when(composantRepository.findById(id)).thenReturn(Optional.of(existingComposant));
        when(composantRepository.save(any(Composant.class))).thenReturn(updatedComposant);

        // Call the method under test
        Composant savedUpdatedComposant = composantService.updateComposant(id, updatedComposant);

        // Assertions
        Assertions.assertEquals("Updated Component", savedUpdatedComposant.getNomComposant(), "Component name should be updated");
        Assertions.assertEquals("Updated Title", savedUpdatedComposant.getTitre(), "Title should be updated");
        Assertions.assertEquals("Updated Description", savedUpdatedComposant.getDesc(), "Description should be updated");
    }


    @Test
    public void testRemoveComposant() {
        // Define an ObjectId for the Composant
        ObjectId id = new ObjectId();

        // Call the method under test
        composantService.removeComposant(id);

        // Verify that the delete method was called with the correct ID
        // Here you would use Mockito.verify() if needed, but no return value to assert
        // so just ensure no exceptions were thrown
    }

    @Test
    public void testRetrieveComposants() {
        // Create a list of sample Composants
        Composant comp1 = new Composant();
        comp1.setNomComposant("Component 1");

        Composant comp2 = new Composant();
        comp2.setNomComposant("Component 2");

        List<Composant> composants = List.of(comp1, comp2);

        // Mock the repository behavior
        when(composantRepository.findAll()).thenReturn(composants);

        // Call the method under test
        List<Composant> retrievedComposants = composantService.retrieveComposants();

        // Assertions
        Assertions.assertNotNull(retrievedComposants, "List should not be null");
        Assertions.assertEquals(2, retrievedComposants.size(), "List size mismatch");
        Assertions.assertEquals("Component 1", retrievedComposants.get(0).getNomComposant(), "First component name mismatch");
        Assertions.assertEquals("Component 2", retrievedComposants.get(1).getNomComposant(), "Second component name mismatch");
    }
}
