package com.example.backdsdm.services;

import com.example.backdsdm.Repositories.SprintRepository;
import com.example.backdsdm.entities.Sprint;
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
}
