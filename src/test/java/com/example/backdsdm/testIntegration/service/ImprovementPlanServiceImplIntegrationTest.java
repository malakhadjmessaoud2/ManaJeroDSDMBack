package com.example.backdsdm.testIntegration.service;

import com.example.backdsdm.Repositories.ImprovementPlanRepository;
import com.example.backdsdm.entities.ImprovementPlan;
import com.example.backdsdm.services.ImprovementPlanServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test") // Utiliser le profil de test
public class ImprovementPlanServiceImplIntegrationTest {

    @Autowired
    private ImprovementPlanRepository improvementPlanRepository;

    @Autowired
    private ImprovementPlanServiceImpl improvementPlanService;

    @BeforeEach
    public void setUp() {
        // Supprimer toutes les données avant chaque test pour garantir un état propre
        improvementPlanRepository.deleteAll();
    }

    @Test
    public void testGetImprovementPlansByProjectId() {
        // Créer et sauvegarder des objets ImprovementPlan
        ImprovementPlan plan1 = new ImprovementPlan();
        plan1.setProjectId("456");
        plan1.setContent("Improvement Plan 1");

        ImprovementPlan plan2 = new ImprovementPlan();
        plan2.setProjectId("456");
        plan2.setContent("Improvement Plan 2");

        improvementPlanRepository.save(plan1);
        improvementPlanRepository.save(plan2);

        // Appel de la méthode sous test
        List<ImprovementPlan> retrievedPlans = improvementPlanService.getImprovementPlansByProjectId("456");

        // Assertions
        Assertions.assertNotNull(retrievedPlans, "La liste des plans d'amélioration ne doit pas être nulle");
        Assertions.assertEquals(2, retrievedPlans.size(), "La taille de la liste des plans d'amélioration doit être de 2");
        Assertions.assertEquals("Improvement Plan 1", retrievedPlans.get(0).getContent(), "Mismatch du contenu du premier plan");
        Assertions.assertEquals("Improvement Plan 2", retrievedPlans.get(1).getContent(), "Mismatch du contenu du second plan");
    }

    @Test
    public void testAddImprovementPlan() {
        // Appel de la méthode sous test
        ImprovementPlan savedPlan = improvementPlanService.addImprovementPlan("456", "New Improvement Plan");

        // Assertions
        Assertions.assertNotNull(savedPlan, "Le plan d'amélioration enregistré ne doit pas être nul");
        Assertions.assertEquals("456", savedPlan.getProjectId(), "Mismatch de l'ID du projet");
        Assertions.assertEquals("New Improvement Plan", savedPlan.getContent(), "Mismatch du contenu");
    }
}
