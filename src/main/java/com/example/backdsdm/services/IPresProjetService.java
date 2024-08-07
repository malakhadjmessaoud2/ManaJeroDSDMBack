package com.example.backdsdm.services;

import com.example.backdsdm.entities.PresProjet;
import org.bson.types.ObjectId;

public interface IPresProjetService {
    PresProjet addDsdm(PresProjet dsdm);

    PresProjet retrieveDSdm(ObjectId id);

    PresProjet getDsdmByProjectId(String idProject);

    PresProjet updateDsdm(String idproject, String id, String context, String priorisation, String status, String startDate, String endDate, String idUser);
}
