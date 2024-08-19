package com.example.backdsdm.services;

import com.example.backdsdm.Repositories.SprintRepository;
import com.example.backdsdm.entities.Sprint;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ISprintServiceImp implements ISprintService{
    private final SprintRepository sprintRepository;

    @Override
    public List<Sprint> getSprintsByProjectId(String projectId) {
        return sprintRepository.findByProjectId(projectId);

    }

    @Override
    public Sprint addSprint(String projectId, String name) {
        Sprint sprint = new Sprint();
        sprint.setName(name);
        sprint.setProjectId(projectId);
        return sprintRepository.save(sprint);    }

    @Override
    public Sprint updateSprint(String projectId, String id, String name) {
        Sprint sprint = sprintRepository.findByProjectIdAndId(projectId, id)
                .orElseThrow(() -> new EntityNotFoundException("Sprint not found for projectId: " + projectId + " and sprintId: " + id));
        sprint.setName(name);
        return sprintRepository.save(sprint);
    }
    @Override
    public void deleteIteration(String projectId, String id) {
        Sprint sprint = sprintRepository.findByProjectIdAndId(projectId, id).orElseThrow();
        sprintRepository.deleteById(id);

    }

    @Override
    public Sprint archiveSprint(String projectId, String id) {
        Sprint sprint = sprintRepository.findByProjectIdAndId(projectId, id)
                .orElseThrow(() -> new EntityNotFoundException("Sprint not found for projectId: " + projectId + " and sprintId: " + id));
        sprint.setArchived(true);
        return sprintRepository.save(sprint);
    }
}
