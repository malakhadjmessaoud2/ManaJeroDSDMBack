package com.example.backdsdm.services;

import com.example.backdsdm.entities.ImprovementPlan;

import java.util.List;

public interface IImprovementPlanService {
    List<ImprovementPlan> getImprovementPlansByProjectId(String projectId);
    ImprovementPlan addImprovementPlan(String projectId, String content);

}
