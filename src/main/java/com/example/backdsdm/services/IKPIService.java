package com.example.backdsdm.services;

import com.example.backdsdm.entities.KPI;

import java.util.List;

public interface IKPIService {
    List<KPI> getKPIsByProjectId(String projectId);
    KPI addKPI(String projectId, String name, String value);

    KPI updateKPI(String projectId, String id, String name, String value);

    KPI archiveKPI(String projectId, String id);

    void deleteKPI(String projectId, String id);
}
