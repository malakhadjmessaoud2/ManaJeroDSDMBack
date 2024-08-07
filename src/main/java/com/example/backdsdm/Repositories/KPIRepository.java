package com.example.backdsdm.Repositories;

import com.example.backdsdm.entities.KPI;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface KPIRepository extends MongoRepository<KPI, String> {
    List<KPI> findByProjectId(String projectId);
}
