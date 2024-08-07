package com.example.backdsdm.services;

import com.example.backdsdm.Repositories.ReportRepository;
import com.example.backdsdm.entities.Report;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportServiceImpl implements IReportService {

    private final ReportRepository reportRepository;

    @Override
    public List<Report> getReportsByProjectId(String projectId) {
        return reportRepository.findByProjectId(projectId);
    }

    @Override
    public Report addReport(String projectId, String title, String content) {
        Report report = new Report();
        report.setProjectId(projectId);
        report.setTitle(title);
        report.setContent(content);
        return reportRepository.save(report);
    }
}
