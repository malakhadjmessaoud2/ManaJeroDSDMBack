package com.example.backdsdm.services;

import com.example.backdsdm.Repositories.KPIRepository;
import com.example.backdsdm.entities.KPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class KPIServiceImpl implements IKPIService {
    private final KPIRepository kpiRepository;

    @Override
    public List<KPI> getKPIsByProjectId(String projectId) {
        return kpiRepository.findByProjectId(projectId);
    }

    @Override
    public KPI addKPI(String projectId, String name, String value) {
        KPI kpi = new KPI();
        kpi.setProjectId(projectId);
        kpi.setName(name);
        kpi.setValue(value);
        return kpiRepository.save(kpi);
    }
    @Override
    public KPI updateKPI(String projectId, String id, String name, String value) {
        KPI kpi = kpiRepository.findByProjectIdAndId(projectId, id)
                .orElseThrow();
        kpi.setName(name);
        kpi.setValue(value);
        return kpiRepository.save(kpi);
    }

    @Override
    public KPI archiveKPI(String projectId, String id) {
        KPI kpi = kpiRepository.findByProjectIdAndId(projectId, id)
                .orElseThrow();
        kpi.setArchived(true);
        return kpiRepository.save(kpi);
    }

    @Override
    public void deleteKPI(String projectId, String id) {
        KPI kpi = kpiRepository.findByProjectIdAndId(projectId, id)
                .orElseThrow();
        kpiRepository.deleteById(id);
    }
}
