package com.example.backdsdm.services;

import com.example.backdsdm.Repositories.FeedbackRepository;
import com.example.backdsdm.entities.Feedback;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedbackServiceImpl implements IFeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Override
    public List<Feedback> getFeedbacksByProjectId(String projectId) {
        return feedbackRepository.findByProjectId(projectId);
    }

    @Override
    public Feedback addFeedback(String projectId, String content) {
        Feedback feedback = new Feedback();
        feedback.setProjectId(projectId);
        feedback.setContent(content);
        return feedbackRepository.save(feedback);
    }
}
