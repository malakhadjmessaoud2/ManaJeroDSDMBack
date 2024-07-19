package com.example.backdsdm.services;

import com.example.backdsdm.Repositories.AdvantageRepository;
import com.example.backdsdm.Repositories.LimitationRepository;
import com.example.backdsdm.entities.Advantage;
import com.example.backdsdm.entities.Limitation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ILimitationImp implements ILimitationService{
    private final LimitationRepository limitationRepository;

    @Override
    public Limitation addLim(Limitation limitation) {
        return limitationRepository.save(limitation);    }

    @Override
    public Limitation retrieveLim(ObjectId id) {
        return limitationRepository.findById(id).orElseThrow(()->new IllegalArgumentException("No limitation found with this id"));
    }

    @Override
    public List<Limitation> retrieveLims() {
        return (List<Limitation>) limitationRepository.findAll();    }

    @Override
    public Limitation updateLim(ObjectId id, Limitation existingLim) {
        Limitation Lim = limitationRepository.findById(id).orElseThrow();
        Lim.setTitre(existingLim.getTitre());
        Lim.setDesc(existingLim.getDesc());
        Lim.setImageUrl(existingLim.getImageUrl());
        return limitationRepository.save(existingLim);    }

    @Override
    public void removeLim(ObjectId id) {
        limitationRepository.deleteById(id);

    }
}
