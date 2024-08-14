package com.example.backdsdm.testIntegration.service;

import com.example.backdsdm.Repositories.FeedbackRepository;
import com.example.backdsdm.entities.Feedback;
import com.example.backdsdm.services.FeedbackServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class FeedbackServiceImplIntegrationTest {

    @Autowired
    private FeedbackServiceImpl feedbackService;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @BeforeEach
    public void setUp() {
        feedbackRepository.deleteAll();
    }

    @Test
    public void testAddFeedback() {
        // Ajouter un nouveau Feedback
        Feedback feedback = feedbackService.addFeedback("projectId123", "This is a feedback.");

        // Vérifier que le feedback a été ajouté
        Assertions.assertNotNull(feedback, "Le feedback enregistré ne doit pas être nul");
        Assertions.assertEquals("projectId123", feedback.getProjectId(), "Le projectId doit correspondre");
        Assertions.assertEquals("This is a feedback.", feedback.getContent(), "Le contenu doit correspondre");
    }

    @Test
    public void testGetFeedbacksByProjectId() {
        // Ajouter deux Feedbacks
        feedbackService.addFeedback("projectId123", "This is feedback 1.");
        feedbackService.addFeedback("projectId123", "This is feedback 2.");

        // Récupérer les feedbacks par projectId
        List<Feedback> feedbacks = feedbackService.getFeedbacksByProjectId("projectId123");

        // Vérifier les résultats
        Assertions.assertNotNull(feedbacks, "Les feedbacks ne doivent pas être nuls");
        Assertions.assertEquals(2, feedbacks.size(), "Le nombre de feedbacks doit correspondre");
        Assertions.assertEquals("This is feedback 1.", feedbacks.get(0).getContent(), "Le contenu du premier feedback doit correspondre");
        Assertions.assertEquals("This is feedback 2.", feedbacks.get(1).getContent(), "Le contenu du deuxième feedback doit correspondre");
    }
}
