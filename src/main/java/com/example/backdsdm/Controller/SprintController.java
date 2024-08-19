package com.example.backdsdm.Controller;

import com.example.backdsdm.entities.Sprint;
import com.example.backdsdm.services.ISprintService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sprint")
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200" )
public class SprintController {
    @Autowired
    private ISprintService sprintService;

    @GetMapping("/showSprints/{projectId}")
    public List<Sprint> getSprintsByProjectId(@PathVariable String projectId) {
        return sprintService.getSprintsByProjectId(projectId);
    }

    @PostMapping("/addSprint/{projectId}")
    public Sprint addSprint(@PathVariable String projectId, @RequestParam String name) {

        return sprintService.addSprint(projectId, name);
    }
    @PutMapping("/updateSprint/{projectId}/{id}")
    public Sprint updateSprint(@PathVariable String projectId, @PathVariable String id, @RequestParam String name) {
        return sprintService.updateSprint(projectId, id, name);
    }
    @PutMapping("/archiveSprint/{projectId}/{id}")
    public Sprint archiveSprint(@PathVariable String projectId, @PathVariable String id) {
        return sprintService.archiveSprint(projectId, id);
    }

    @DeleteMapping("/deleteSprint/{projectId}/{id}")
    public void deleteIteration(@PathVariable String projectId, @PathVariable String id) {
        sprintService.deleteIteration(projectId, id);
    }
}
