package com.example.backdsdm.Controller;

import com.example.backdsdm.entities.DeploymentPlan;
import com.example.backdsdm.services.IDeploymentPlanService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("deploymentPlan")
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class DeploymentPlanController {
    @Autowired
    private IDeploymentPlanService deploymentPlanService;

    @GetMapping("/showDeploymentPlans/{projectId}")
    public List<DeploymentPlan> getDeploymentPlansByProjectId(@PathVariable String projectId) {
        return deploymentPlanService.getDeploymentPlansByProjectId(projectId);
    }

    @PostMapping("/addDeploymentPlan/{projectId}")
    public DeploymentPlan addDeploymentPlan(
            @PathVariable String projectId,
            @RequestParam String environment,
            @RequestParam String dataMigration,
            @RequestParam String preProdTests
    ) {
        return deploymentPlanService.addDeploymentPlan(projectId, environment, dataMigration, preProdTests);
    }

}
