package com.example.backdsdm.services;

import com.example.backdsdm.entities.Limitation;
import org.bson.types.ObjectId;

import java.util.List;

public interface ILimitationService {
    Limitation addLim(Limitation limitation);

    Limitation retrieveLim(ObjectId id);

    List<Limitation> retrieveLims();

    Limitation updateLim(ObjectId id, Limitation existingLim);

    void removeLim(ObjectId id);
}
