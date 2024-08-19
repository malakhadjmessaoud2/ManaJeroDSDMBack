package com.example.backdsdm.controller;

import com.example.backdsdm.Controller.FeedbackController;
import com.example.backdsdm.entities.Feedback;
import com.example.backdsdm.services.IFeedbackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FeedbackControllerTest {

    @Mock
    private IFeedbackService feedbackService;

    @InjectMocks
    private FeedbackController feedbackController;

    @BeforeEach
    public void setUp() {
        // Initialization logic if needed
    }

    @Test
    public void testGetFeedbacksByProjectId() {
        // Create sample Feedbacks
        Feedback feedback1 = new Feedback();
        feedback1.setProjectId("projectId123");
        feedback1.setContent("This is feedback 1.");

        Feedback feedback2 = new Feedback();
        feedback2.setProjectId("projectId123");
        feedback2.setContent("This is feedback 2.");

        List<Feedback> feedbacks = Arrays.asList(feedback1, feedback2);

        // Mock the service behavior
        when(feedbackService.getFeedbacksByProjectId("projectId123")).thenReturn(feedbacks);

        // Call the controller method
        List<Feedback> foundFeedbacks = feedbackController.getFeedbacksByProjectId("projectId123");

        // Assertions
        assertNotNull(foundFeedbacks, "Feedbacks should not be null");
        assertEquals(2, foundFeedbacks.size(), "The number of Feedbacks should match");
        assertEquals("This is feedback 1.", foundFeedbacks.get(0).getContent(), "Content mismatch for feedback 1");
        assertEquals("This is feedback 2.", foundFeedbacks.get(1).getContent(), "Content mismatch for feedback 2");
    }

    @Test
    public void testAddFeedback() {
        // Create a sample Feedback
        Feedback feedback = new Feedback();
        feedback.setProjectId("projectId123");
        feedback.setContent("This is a feedback.");

        // Mock the service behavior
        when(feedbackService.addFeedback(anyString(), anyString())).thenReturn(feedback);

        // Call the controller method
        Feedback savedFeedback = feedbackController.addFeedback("projectId123", "This is a feedback.");

        // Assertions
        assertNotNull(savedFeedback, "Saved Feedback should not be null");
        assertEquals("projectId123", savedFeedback.getProjectId(), "Project ID mismatch");
        assertEquals("This is a feedback.", savedFeedback.getContent(), "Content mismatch");
    }
}
