package com.example.backdsdm.Repositories;

import com.example.backdsdm.entities.Report;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ReportRepository  extends MongoRepository<Report, String> {
    List<Report> findByProjectId(String projectId);
    Optional<Report> findByProjectIdAndId(String projectId, String id);

}
