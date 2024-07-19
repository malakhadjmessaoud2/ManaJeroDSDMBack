package com.example.backdsdm.services;

import com.example.backdsdm.entities.Introduction;
import org.bson.types.ObjectId;

import java.util.List;

public interface IIntroductionService {
    Introduction addintro(Introduction introduction);

    List<Introduction> retrieveIntros();

    Introduction retrieveIntro(ObjectId idIntro);

    Introduction updateIntro(ObjectId id, Introduction existingIntro);

    void removeIntro(ObjectId idinto);
}
