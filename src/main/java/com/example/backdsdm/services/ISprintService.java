package com.example.backdsdm.services;

import com.example.backdsdm.entities.Sprint;

import java.util.List;

public interface ISprintService {
    List<Sprint> getSprintsByProjectId(String projectId);
    Sprint addSprint(String projectId, String name);

    Sprint updateSprint(String projectId, String id, String name);

    void deleteIteration(String projectId, String id);

    Sprint archiveSprint(String projectId, String id);
}
