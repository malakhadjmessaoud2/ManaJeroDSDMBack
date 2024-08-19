package com.example.backdsdm.Controller;

import com.example.backdsdm.entities.Feasibility;
import com.example.backdsdm.entities.Foundation;
import com.example.backdsdm.services.IFoundationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("foundation")
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class FoundationController {
    @Autowired
    private IFoundationService foundationService;

    @PostMapping("addfoundation/{projectId}")
    public ResponseEntity<Foundation> addFoundation(@PathVariable String projectId,
                                                    @RequestParam String projectVision,
                                                    @RequestParam String userNeeds,
                                                    @RequestParam String projectCharter,
                                                    @RequestParam String requirements,
                                                    @RequestParam String idUser) {
        Foundation foundation = new Foundation();
        foundation.setProject(projectId);
        foundation.setProjectVision(projectVision);
        foundation.setUserNeeds(userNeeds);
        foundation.setProjectCharter(projectCharter);
        foundation.setRequirements(requirements);
        foundation.setIdUser(idUser);

        Foundation createdFoundation = foundationService.addFoundation(foundation);
        return ResponseEntity.ok(createdFoundation);
    }

    @GetMapping("showfoundation/{project}")
    public Foundation getFoundationByProject(@PathVariable String project) {
         return foundationService.getFoundationByProject(project);


    }

    @PutMapping("/updatefoundation/{project}/{id}")
    public ResponseEntity<Foundation> updateFoundation(
            @PathVariable String project,
            @PathVariable String id,
            @RequestParam String projectVision,
            @RequestParam String userNeeds,
            @RequestParam String projectCharter,
            @RequestParam String requirements,
            @RequestParam String id_user) {
        Foundation updatedFoundation = foundationService.updateFoundation(project, id, projectVision, userNeeds, projectCharter, requirements, id_user);
        return ResponseEntity.ok(updatedFoundation);
    }
}
