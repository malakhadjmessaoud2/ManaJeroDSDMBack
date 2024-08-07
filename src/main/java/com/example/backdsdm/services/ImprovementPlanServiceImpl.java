package com.example.backdsdm.services;

import com.example.backdsdm.Repositories.ImprovementPlanRepository;
import com.example.backdsdm.entities.ImprovementPlan;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImprovementPlanServiceImpl implements IImprovementPlanService {

    private final ImprovementPlanRepository improvementPlanRepository;

    @Override
    public List<ImprovementPlan> getImprovementPlansByProjectId(String projectId) {
        return improvementPlanRepository.findByProjectId(projectId);
    }

    @Override
    public ImprovementPlan addImprovementPlan(String projectId, String content) {
        ImprovementPlan improvementPlan = new ImprovementPlan();
        improvementPlan.setProjectId(projectId);
        improvementPlan.setContent(content);
        return improvementPlanRepository.save(improvementPlan);
    }
}
