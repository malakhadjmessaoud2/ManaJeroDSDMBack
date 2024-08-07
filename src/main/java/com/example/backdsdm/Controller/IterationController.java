package com.example.backdsdm.Controller;

import com.example.backdsdm.entities.Iteration;
import com.example.backdsdm.services.IterationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("iteration")
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200" )
public class IterationController {
    @Autowired
    private IterationService iterationService;

    @GetMapping("/ShowIterations/{sprintId}")
    public List<Iteration> getIterationsBySprintId(@PathVariable String sprintId) {
        return iterationService.getIterationsBySprintId(sprintId);
    }

    @PostMapping("/addIteration/{sprintId}")
    public Iteration addIteration(@PathVariable String sprintId,
                                  @RequestParam String feature,
                                  @RequestParam String deliverables) {
        return iterationService.addIteration(sprintId, feature, deliverables);
    }
}
