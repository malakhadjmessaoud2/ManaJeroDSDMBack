package com.example.backdsdm.Controller;

import com.example.backdsdm.entities.Release;
import com.example.backdsdm.services.IReleaseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("release")
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200" )
public class ReleaseController {
    @Autowired
    private IReleaseService releaseService;

    @GetMapping("/showReleases/{deploymentPlanId}")
    public List<Release> getReleasesByDeploymentPlanId(@PathVariable String deploymentPlanId) {
        return releaseService.getReleasesByDeploymentPlanId(deploymentPlanId);
    }

    @PostMapping("/addRelease/{deploymentPlanId}")
    public Release addRelease(
            @PathVariable String deploymentPlanId,
            @RequestParam String name,
            @RequestParam String details
    ) {
        return releaseService.addRelease(deploymentPlanId, name, details);
    }
}
