package com.example.backdsdm.services;

import com.example.backdsdm.entities.tutoDSDM;
import org.bson.types.ObjectId;

public interface ITutoDSDMService {
    tutoDSDM addTutoDsdm(tutoDSDM t);

    tutoDSDM updateTutoDsdm(tutoDSDM t);

    tutoDSDM retrieveTutoDSDM(ObjectId idTuto);
    void removeTutoDsdm(ObjectId idTuto);

}
