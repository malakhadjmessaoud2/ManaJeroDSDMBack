package com.example.backdsdm.services;

import com.example.backdsdm.entities.Feasibility;
import org.bson.types.ObjectId;

public interface IFeasibilityService {
    Feasibility addFeasibility(Feasibility feasibility);
    Feasibility getFeasibilityByProjectId(String idProject);

    Feasibility updateFeasibility(String idProject, String id, String technicalFeasibility, String commercialFeasibility, String mvp, String releaseBoard, String viability, String idUser);
}
