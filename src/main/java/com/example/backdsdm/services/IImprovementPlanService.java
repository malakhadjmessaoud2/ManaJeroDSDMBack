package com.example.backdsdm.services;

import com.example.backdsdm.entities.ImprovementPlan;

import java.util.List;

public interface IImprovementPlanService {
    List<ImprovementPlan> getImprovementPlansByProjectId(String projectId);
    ImprovementPlan addImprovementPlan(String projectId, String content);

    ImprovementPlan updateImprovementPlan(String projectId, String id, String content);

    ImprovementPlan archiveImprovementPlan(String projectId, String id);

    void deleteImprovementPlan(String projectId, String id);
}
