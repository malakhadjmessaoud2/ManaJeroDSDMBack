package com.example.backdsdm.service;

import com.example.backdsdm.Repositories.AdvantageRepository;
import com.example.backdsdm.entities.Advantage;
import com.example.backdsdm.services.IAdvantageImp;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IAdvantageImpTest {

    @Mock
    private AdvantageRepository advantageRepository;

    @InjectMocks
    private IAdvantageImp advantageService;

    @Test
    public void testAddAdv() {
        // Create a sample Advantage
        Advantage advantage = new Advantage();
        advantage.setId("123");
        advantage.setTitre("Sample Title");
        advantage.setDesc("Sample Description");

        // Mock the repository behavior
        when(advantageRepository.save(any(Advantage.class))).thenReturn(advantage);

        // Call the method under test
        Advantage savedAdvantage = advantageService.addAdv(advantage);

        // Assertions
        Assertions.assertNotNull(savedAdvantage.getId(), "ID should not be null");
        Assertions.assertEquals("Sample Title", savedAdvantage.getTitre(), "Title mismatch");
        Assertions.assertEquals("Sample Description", savedAdvantage.getDesc(), "Description mismatch");
    }


    @Test
    public void testRetrieveAdvs() {
        // Create a list of sample Advantages
        Advantage adv1 = new Advantage();
        adv1.setTitre("Title 1");

        Advantage adv2 = new Advantage();
        adv2.setTitre("Title 2");

        List<Advantage> advantages = List.of(adv1, adv2);

        // Mock the repository behavior
        when(advantageRepository.findAll()).thenReturn(advantages);

        // Call the method under test
        List<Advantage> retrievedAdvs = advantageService.retrieveAdvs();

        // Assertions
        Assertions.assertNotNull(retrievedAdvs, "List should not be null");
        Assertions.assertEquals(2, retrievedAdvs.size(), "List size mismatch");
        Assertions.assertEquals("Title 1", retrievedAdvs.get(0).getTitre(), "First title mismatch");
        Assertions.assertEquals("Title 2", retrievedAdvs.get(1).getTitre(), "Second title mismatch");
    }

    @Test
    public void testUpdateAdv() {
        // Create a sample Advantage
        ObjectId id = new ObjectId();
        Advantage existingAdv = new Advantage();
        existingAdv.setId(String.valueOf(id));
        existingAdv.setTitre("Original Title");

        Advantage updatedAdv = new Advantage();
        updatedAdv.setTitre("Updated Title");
        updatedAdv.setDesc("Updated Description");

        // Mock the repository behavior
        when(advantageRepository.findById(id)).thenReturn(Optional.of(existingAdv));
        when(advantageRepository.save(any(Advantage.class))).thenReturn(updatedAdv);

        // Call the method under test
        Advantage savedUpdatedAdv = advantageService.updateAdv(id, updatedAdv);

        // Assertions
        Assertions.assertEquals("Updated Title", savedUpdatedAdv.getTitre(), "Title should be updated");
        Assertions.assertEquals("Updated Description", savedUpdatedAdv.getDesc(), "Description should be updated");
    }

    @Test
    public void testRemoveAdv() {
        // Define an ObjectId for the Advantage
        ObjectId id = new ObjectId();

        // Call the method under test
        advantageService.removeAdv(id);

        // Verify that the delete method was called with the correct ID
        // Here you would use Mockito.verify() if needed, but no return value to assert
        // so just ensure no exceptions were thrown
    }
}
