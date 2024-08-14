package com.example.backdsdm.Repositories;

import com.example.backdsdm.entities.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface FeedbackRepository extends MongoRepository<Feedback, String> {
    List<Feedback> findByProjectId(String projectId);
    Optional<Feedback> findByProjectIdAndId(String projectId, String id);

}