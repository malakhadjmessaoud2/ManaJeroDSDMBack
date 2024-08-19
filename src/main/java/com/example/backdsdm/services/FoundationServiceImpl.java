package com.example.backdsdm.services;

import com.example.backdsdm.Repositories.FoundationRepository;
import com.example.backdsdm.entities.Foundation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FoundationServiceImpl implements IFoundationService{
    private final FoundationRepository foundationRepository;
    @Override
    public Foundation addFoundation(Foundation foundation) {
        return foundationRepository.save(foundation);
    }

    @Override
    public Foundation getFoundationByProject(String project) {
        return foundationRepository.findByProject(project);
    }
    @Override
    public Foundation updateFoundation(String project, String id, String projectVision, String userNeeds, String projectCharter, String requirements, String idUser) {
        Optional<Foundation> optionalFoundation = foundationRepository.findByIdAndProject(id, project);

        if (!optionalFoundation.isPresent()) {
            throw new IllegalArgumentException("Foundation not found");
        }

        Foundation foundation = optionalFoundation.get();
        foundation.setProjectVision(projectVision);
        foundation.setUserNeeds(userNeeds);
        foundation.setProjectCharter(projectCharter);
        foundation.setRequirements(requirements);
        foundation.setIdUser(idUser);

        return foundationRepository.save(foundation);
    }

}
