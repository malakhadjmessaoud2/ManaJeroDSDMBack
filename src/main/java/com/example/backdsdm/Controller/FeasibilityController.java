package com.example.backdsdm.Controller;

import com.example.backdsdm.entities.Feasibility;
import com.example.backdsdm.entities.PresProjet;
import com.example.backdsdm.services.IFeasibilityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("feasibility")
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class FeasibilityController {
    @Autowired
    private IFeasibilityService iFeasibilityService;

    @PostMapping("addfeasibility/{id_project}")
    public Feasibility addFeasibility(
            @PathVariable String id_project,
            @RequestParam String technicalFeasibility,
            @RequestParam String commercialFeasibility,
            @RequestParam String mvp,
            @RequestParam String releaseBoard,
            @RequestParam String viability,
            @RequestParam String id_user
    ) throws IOException {
        Feasibility feasibility = new Feasibility();
        feasibility.setTechnicalFeasibility(technicalFeasibility);
        feasibility.setCommercialFeasibility(commercialFeasibility);
        feasibility.setMvp(mvp);
        feasibility.setReleaseBoard(releaseBoard);
        feasibility.setViability(viability);
        feasibility.setIdproject(id_project);
        feasibility.setId_user(id_user);
        return iFeasibilityService.addFeasibility(feasibility);
    }
    @PutMapping("/updatefeasibility/{id_project}/{id}")
    public ResponseEntity<Feasibility> updateFeasibility(
            @PathVariable String id_project,
            @PathVariable String id,
            @RequestParam String technicalFeasibility,
            @RequestParam String commercialFeasibility,
            @RequestParam String mvp,
            @RequestParam String releaseBoard,
            @RequestParam String viability,
            @RequestParam String id_user) {
        Feasibility updateFeasibility = iFeasibilityService.updateFeasibility(id_project, id, technicalFeasibility, commercialFeasibility, mvp, releaseBoard, viability, id_user);
        return ResponseEntity.ok(updateFeasibility);
    }
    @GetMapping("/showfeasibility/{id_project}")
    public Feasibility getFeasibilityByProjectId(@PathVariable String id_project) {
        return iFeasibilityService.getFeasibilityByProjectId(id_project);
    }


}
