package com.example.backdsdm.service;

import com.example.backdsdm.Repositories.FeedbackRepository;
import com.example.backdsdm.entities.Feedback;
import com.example.backdsdm.services.FeedbackServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FeedbackServiceImplTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    @InjectMocks
    private FeedbackServiceImpl feedbackService;

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

        // Mock the repository behavior
        when(feedbackRepository.findByProjectId("projectId123")).thenReturn(feedbacks);

        // Call the method under test
        List<Feedback> foundFeedbacks = feedbackService.getFeedbacksByProjectId("projectId123");

        // Assertions
        Assertions.assertNotNull(foundFeedbacks, "Feedbacks should not be null");
        Assertions.assertEquals(2, foundFeedbacks.size(), "The number of Feedbacks should match");
        Assertions.assertEquals("This is feedback 1.", foundFeedbacks.get(0).getContent(), "Content mismatch for feedback 1");
        Assertions.assertEquals("This is feedback 2.", foundFeedbacks.get(1).getContent(), "Content mismatch for feedback 2");
    }

    @Test
    public void testAddFeedback() {
        // Create a sample Feedback
        Feedback feedback = new Feedback();
        feedback.setProjectId("projectId123");
        feedback.setContent("This is a feedback.");

        // Mock the repository behavior
        when(feedbackRepository.save(any(Feedback.class))).thenReturn(feedback);

        // Call the method under test
        Feedback savedFeedback = feedbackService.addFeedback("projectId123", "This is a feedback.");

        // Assertions
        Assertions.assertNotNull(savedFeedback, "Saved Feedback should not be null");
        Assertions.assertEquals("projectId123", savedFeedback.getProjectId(), "Project ID mismatch");
        Assertions.assertEquals("This is a feedback.", savedFeedback.getContent(), "Content mismatch");
    }
}
