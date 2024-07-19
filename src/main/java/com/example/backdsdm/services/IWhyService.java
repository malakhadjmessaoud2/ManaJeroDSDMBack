package com.example.backdsdm.services;

import com.example.backdsdm.entities.Why;
import org.bson.types.ObjectId;

import java.util.List;

public interface IWhyService {
    Why addWhy(Why why);

    Why retrieveWhy(ObjectId id);

    List<Why> retrieveWhys();

    Why updateWhy(ObjectId id, Why existingWhy);

    void removeWhy(ObjectId id);
}
