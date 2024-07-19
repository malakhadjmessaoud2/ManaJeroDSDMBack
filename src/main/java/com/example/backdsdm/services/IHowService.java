package com.example.backdsdm.services;

import com.example.backdsdm.entities.How;
import org.bson.types.ObjectId;

import java.util.List;

public interface IHowService {
    How addHow(How how);

    How retrieveHow(ObjectId id);

    List<How> retrieveHows();

    How updateHow(ObjectId id, How existingHow);

    void removeHow(ObjectId id);
}
