package com.example.backdsdm.services;

import com.example.backdsdm.entities.Report;

import java.util.List;

public interface IReportService {
    List<Report> getReportsByProjectId(String projectId);
    Report addReport(String projectId, String title, String content);

    Report updateReport(String projectId, String id, String title, String content);

    Report archiveReport(String projectId, String id);

    void deleteReport(String projectId, String id);
}
