package com.example.backdsdm.services;

import com.example.backdsdm.entities.Iteration;

import java.util.List;

public interface IterationService {
    List<Iteration> getIterationsBySprintId(String sprintId);
    Iteration addIteration(String sprintId, String feature, String deliverables);

}
