package com.example.backdsdm.services;

import com.example.backdsdm.Repositories.IterationRepository;
import com.example.backdsdm.Repositories.SprintRepository;
import com.example.backdsdm.entities.Iteration;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Iteration updateIteration(String sprintId, String id, String feature, String deliverables) {
        // Recherche de l'itération par sprintId et iterationId
        Iteration iteration = iterationRepository.findBySprintIdAndId(sprintId, id)
                .orElseThrow(() -> new EntityNotFoundException("Iteration not found for sprintId: " + sprintId + " and iterationId: " + id));

        // Mise à jour des champs de l'itération
        iteration.setFeature(feature);
        iteration.setDeliverables(deliverables);

        // Sauvegarde et retour de l'itération mise à jour
        return iterationRepository.save(iteration);

    }

    @Override
    public void deleteIteration(String sprintId, String id) {
        Iteration iteration = iterationRepository.findBySprintIdAndId(sprintId, id).orElseThrow();
        iterationRepository.deleteById(id);

    }
    @Override
    public Iteration archiveIteration(String sprintId, String iterationId) {
        Iteration iteration = iterationRepository.findById(iterationId).orElseThrow();
        iteration.setArchived(true);
        return iterationRepository.save(iteration);
    }
}
