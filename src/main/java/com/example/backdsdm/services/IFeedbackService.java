package com.example.backdsdm.services;

import com.example.backdsdm.entities.Feedback;

import java.util.List;

public interface IFeedbackService {
    List<Feedback> getFeedbacksByProjectId(String projectId);
    Feedback addFeedback(String projectId, String content);

}
