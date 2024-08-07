package com.example.backdsdm.services;

import com.example.backdsdm.Repositories.FeasibilityRepository;
import com.example.backdsdm.entities.Feasibility;
import com.example.backdsdm.entities.PresProjet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class IFeasibilityServiceImp implements IFeasibilityService {
    private final FeasibilityRepository feasibilityRepository;

    @Override
    public Feasibility addFeasibility(Feasibility feasibility) {
        return feasibilityRepository.save(feasibility);
    }

    @Override
    public Feasibility getFeasibilityByProjectId(String idProject) {
        return feasibilityRepository.findByIdproject(idProject);
    }

    @Override
    public Feasibility updateFeasibility(String idProject, String id, String technicalFeasibility, String commercialFeasibility, String mvp, String releaseBoard, String viability, String idUser) {
        Optional<Feasibility> optionalFeasibility = feasibilityRepository.findByIdAndIdproject(id, idProject);

        if (!optionalFeasibility.isPresent()) {
            throw new IllegalArgumentException("Feasibility not found");
        }

        Feasibility feasibility = optionalFeasibility.get();
        feasibility.setTechnicalFeasibility(technicalFeasibility);
        feasibility.setCommercialFeasibility(commercialFeasibility);
        feasibility.setMvp(mvp);
        feasibility.setReleaseBoard(releaseBoard);
        feasibility.setViability(viability);
        feasibility.setId_user(idUser);

        return feasibilityRepository.save(feasibility);
    }
}
