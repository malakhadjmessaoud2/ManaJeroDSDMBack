package com.example.backdsdm.services;

import com.example.backdsdm.entities.Report;

import java.util.List;

public interface IReportService {
    List<Report> getReportsByProjectId(String projectId);
    Report addReport(String projectId, String title, String content);

}
