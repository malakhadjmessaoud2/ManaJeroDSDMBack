package com.example.backdsdm.services;

import com.example.backdsdm.entities.Composant;
import org.bson.types.ObjectId;

import java.util.List;

public interface IComposantService {
    Composant addComposant(Composant composant);

    Composant updateComposant(ObjectId m, Composant composant);

    Composant retrieveComposant(ObjectId idComposant);
    void removeComposant(ObjectId idComposant);

    List<Composant> retrieveComposants();
}
