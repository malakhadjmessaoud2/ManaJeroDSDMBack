package com.example.backdsdm.Controller;

import com.example.backdsdm.entities.KPI;
import com.example.backdsdm.services.IKPIService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("kpi")
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200" )
public class KPIController {
    @Autowired
    private IKPIService kpiService;

    @GetMapping("/showKPIs/{projectId}")
    public List<KPI> getKPIsByProjectId(@PathVariable String projectId) {
        return kpiService.getKPIsByProjectId(projectId);
    }

    @PostMapping("/addKPI/{projectId}")
    public KPI addKPI(
            @PathVariable String projectId,
            @RequestParam String name,
            @RequestParam String value
    ) {
        return kpiService.addKPI(projectId, name, value);
    }
    @PutMapping("/updateKPI/{projectId}/{id}")
    public KPI updateKPI(@PathVariable String projectId, @PathVariable String id,
                         @RequestParam String name,
                         @RequestParam String value) {
        return kpiService.updateKPI(projectId, id, name, value);
    }

    @PutMapping("/archiveKPI/{projectId}/{id}")
    public KPI archiveKPI(@PathVariable String projectId, @PathVariable String id) {
        return kpiService.archiveKPI(projectId, id);
    }

    @DeleteMapping("/deleteKPI/{projectId}/{id}")
    public void deleteKPI(@PathVariable String projectId, @PathVariable String id) {
        kpiService.deleteKPI(projectId, id);
    }
}
