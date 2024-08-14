package com.example.backdsdm.Controller;

import com.example.backdsdm.entities.ImprovementPlan;
import com.example.backdsdm.services.IImprovementPlanService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("improvementPlan")
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ImprovementPlanController {
    @Autowired
    private IImprovementPlanService improvementPlanService;

    @GetMapping("/showImprovementPlans/{projectId}")
    public List<ImprovementPlan> getImprovementPlansByProjectId(@PathVariable String projectId) {
        return improvementPlanService.getImprovementPlansByProjectId(projectId);
    }

    @PostMapping("/addImprovementPlan/{projectId}")
    public ImprovementPlan addImprovementPlan(
            @PathVariable String projectId,
            @RequestParam String content
    ) {
        return improvementPlanService.addImprovementPlan(projectId, content);
    }
    @PutMapping("/updateImprovementPlan/{projectId}/{id}")
    public ImprovementPlan updateImprovementPlan(
            @PathVariable String projectId,
            @PathVariable String id,
            @RequestParam String content) {
        return improvementPlanService.updateImprovementPlan(projectId, id, content);
    }

    @PutMapping("/archiveImprovementPlan/{projectId}/{id}")
    public ImprovementPlan archiveImprovementPlan(
            @PathVariable String projectId,
            @PathVariable String id) {
        return improvementPlanService.archiveImprovementPlan(projectId, id);
    }

    @DeleteMapping("/deleteImprovementPlan/{projectId}/{id}")
    public void deleteImprovementPlan(
            @PathVariable String projectId,
            @PathVariable String id) {
        improvementPlanService.deleteImprovementPlan(projectId, id);
    }
}
