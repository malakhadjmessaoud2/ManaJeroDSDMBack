package com.example.backdsdm.services;

import com.example.backdsdm.Repositories.AdvantageRepository;
import com.example.backdsdm.Repositories.HowRepository;
import com.example.backdsdm.entities.Advantage;
import com.example.backdsdm.entities.How;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class IHowImp implements IHowService{
    private final HowRepository howRepository;

    @Override
    public How addHow(How how) {
        return howRepository.save(how);
    }

    @Override
    public How retrieveHow(ObjectId id) {
        return howRepository.findById(id).orElseThrow(()->new IllegalArgumentException("No How found with this id"));
    }

    @Override
    public List<How> retrieveHows() {
        return (List<How>) howRepository.findAll();
    }

    @Override
    public How updateHow(ObjectId id, How existingHow) {
        How how = howRepository.findById(id).orElseThrow();
        how.setTitre(existingHow.getTitre());
        how.setDesc(existingHow.getDesc());
        how.setImageUrl(existingHow.getImageUrl());
        return howRepository.save(existingHow);
    }

    @Override
    public void removeHow(ObjectId id) {
        howRepository.deleteById(id);

    }
}
