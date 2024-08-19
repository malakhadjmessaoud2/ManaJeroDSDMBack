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
    @PutMapping("/updateRelease/{deploymentPlanId}/{id}")
    public Release updateRelease(@PathVariable String deploymentPlanId,
                                 @PathVariable String id,
                                 @RequestParam String name,
                                 @RequestParam String details) {
        return releaseService.updateRelease(deploymentPlanId, id, name, details);
    }

    @PutMapping("/archiveRelease/{deploymentPlanId}/{id}")
    public Release archiveRelease(@PathVariable String deploymentPlanId, @PathVariable String id) {
        return releaseService.archiveRelease(deploymentPlanId, id);
    }

    @DeleteMapping("/deleteRelease/{deploymentPlanId}/{id}")
    public void deleteRelease(@PathVariable String deploymentPlanId, @PathVariable String id) {
        releaseService.deleteRelease(deploymentPlanId, id);
    }
}
