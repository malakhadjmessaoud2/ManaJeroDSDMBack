package com.example.backdsdm.Controller;

import com.example.backdsdm.entities.Feedback;
import com.example.backdsdm.services.IFeedbackService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("feedback")
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class FeedbackController {
    @Autowired
    private IFeedbackService feedbackService;

    @GetMapping("/showFeedbacks/{projectId}")
    public List<Feedback> getFeedbacksByProjectId(@PathVariable String projectId) {
        return feedbackService.getFeedbacksByProjectId(projectId);
    }

    @PostMapping("/addFeedback/{projectId}")
    public Feedback addFeedback(
            @PathVariable String projectId,
            @RequestParam String content
    ) {
        return feedbackService.addFeedback(projectId, content);
    }
    @PutMapping("/updateFeedback/{projectId}/{id}")
    public Feedback updateFeedback(@PathVariable String projectId, @PathVariable String id,
                                   @RequestParam String content) {
        return feedbackService.updateFeedback(projectId, id, content);
    }

    @PutMapping("/archiveFeedback/{projectId}/{id}")
    public Feedback archiveFeedback(@PathVariable String projectId, @PathVariable String id) {
        return feedbackService.archiveFeedback(projectId, id);
    }

    @DeleteMapping("/deleteFeedback/{projectId}/{id}")
    public void deleteFeedback(@PathVariable String projectId, @PathVariable String id) {
        feedbackService.deleteFeedback(projectId, id);
    }
}
