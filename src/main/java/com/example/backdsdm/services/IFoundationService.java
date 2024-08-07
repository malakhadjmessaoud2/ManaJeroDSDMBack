package com.example.backdsdm.services;

import com.example.backdsdm.entities.Foundation;

public interface IFoundationService {
    Foundation addFoundation(Foundation foundation);

    Foundation getFoundationByProject(String projectId);

    Foundation updateFoundation(String project, String id, String projectVision, String userNeeds, String projectCharter, String requirements, String idUser);
}
