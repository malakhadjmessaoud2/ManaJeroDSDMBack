package com.example.backdsdm.Controller;

import com.example.backdsdm.entities.Report;
import com.example.backdsdm.services.IReportService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("report")
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200" )
public class ReportController {
    @Autowired
    private IReportService reportService;

    @GetMapping("/showReports/{projectId}")
    public List<Report> getReportsByProjectId(@PathVariable String projectId) {
        return reportService.getReportsByProjectId(projectId);
    }

    @PostMapping("/addReport/{projectId}")
    public Report addReport(
            @PathVariable String projectId,
            @RequestParam String title,
            @RequestParam String content
    ) {
        return reportService.addReport(projectId, title, content);
    }
    @PutMapping("/updateReport/{projectId}/{id}")
    public Report updateReport(@PathVariable String projectId, @PathVariable String id,
                               @RequestParam String title,
                               @RequestParam String content) {
        return reportService.updateReport(projectId, id, title, content);
    }

    @PutMapping("/archiveReport/{projectId}/{id}")
    public Report archiveReport(@PathVariable String projectId, @PathVariable String id) {
        return reportService.archiveReport(projectId, id);
    }

    @DeleteMapping("/deleteReport/{projectId}/{id}")
    public void deleteReport(@PathVariable String projectId, @PathVariable String id) {
        reportService.deleteReport(projectId, id);
    }
}
