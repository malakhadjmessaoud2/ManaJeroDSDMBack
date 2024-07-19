package com.example.backdsdm.services;

import com.example.backdsdm.Repositories.IntroductionRepository;
import com.example.backdsdm.entities.Composant;
import com.example.backdsdm.entities.Introduction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class IIntroductionImp implements IIntroductionService{
    private final IntroductionRepository introductionRepository;

    @Override
    public Introduction addintro(Introduction introduction) {
        return introductionRepository.save(introduction);
    }

    @Override
    public List<Introduction> retrieveIntros() {
        return (List<Introduction>) introductionRepository.findAll();    }

    @Override
    public Introduction retrieveIntro(ObjectId idIntro) {
        return introductionRepository.findById(idIntro).orElseThrow(()->new IllegalArgumentException("No introduction found with this id"));
    }

    @Override
    public Introduction updateIntro(ObjectId id, Introduction existingIntro) {
        Introduction Intro = introductionRepository.findById(id).orElseThrow();

        Intro.setTitre(existingIntro.getTitre());
        Intro.setDesc(existingIntro.getDesc());
        Intro.setImageUrl(existingIntro.getImageUrl());
        return introductionRepository.save(existingIntro);
    }

    @Override
    public void removeIntro(ObjectId idinto) {
        introductionRepository.deleteById(idinto);

    }
}
