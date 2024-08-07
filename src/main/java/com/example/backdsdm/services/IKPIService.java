package com.example.backdsdm.services;

import com.example.backdsdm.entities.KPI;

import java.util.List;

public interface IKPIService {
    List<KPI> getKPIsByProjectId(String projectId);
    KPI addKPI(String projectId, String name, String value);
}
