package com.example.backdsdm.services;

import com.example.backdsdm.Repositories.IterationRepository;
import com.example.backdsdm.Repositories.SprintRepository;
import com.example.backdsdm.entities.Iteration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class IterationServiceImpl implements IterationService {

    private final IterationRepository iterationRepository;

    private final SprintRepository sprintRepository;

    @Override
    public List<Iteration> getIterationsBySprintId(String sprintId) {
        return iterationRepository.findBySprintId(sprintId);
    }

    @Override
    public Iteration addIteration(String sprintId, String feature, String deliverables) {
        Iteration iteration = new Iteration();
        iteration.setFeature(feature);
        iteration.setDeliverables(deliverables);
        iteration.setSprintId(sprintId);
        return iterationRepository.save(iteration);
    }
}
