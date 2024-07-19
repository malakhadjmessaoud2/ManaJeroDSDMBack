package com.example.backdsdm.services;

import com.example.backdsdm.entities.Advantage;
import org.bson.types.ObjectId;

import java.util.List;

public interface IAdvantageService {
    Advantage addAdv(Advantage advantage);

    Advantage retrieveAdv(ObjectId id);

    List<Advantage> retrieveAdvs();

    Advantage updateAdv(ObjectId id, Advantage existingAdv);

    void removeAdv(ObjectId id);
}
