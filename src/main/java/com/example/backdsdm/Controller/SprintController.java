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
}
