package com.example.backdsdm.services;

import com.example.backdsdm.Repositories.LimitationRepository;
import com.example.backdsdm.Repositories.WhyRepository;
import com.example.backdsdm.entities.Limitation;
import com.example.backdsdm.entities.Why;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class IWhyImp implements IWhyService {
    private final WhyRepository whyRepository;

    @Override
    public Why addWhy(Why why) {
        return whyRepository.save(why);    }

    @Override
    public Why retrieveWhy(ObjectId id) {
        return whyRepository.findById(id).orElseThrow(()->new IllegalArgumentException("No Why found with this id"));
    }

    @Override
    public List<Why> retrieveWhys() {
        return (List<Why>) whyRepository.findAll();      }

    @Override
    public Why updateWhy(ObjectId id, Why existingWhy) {
        Why why = whyRepository.findById(id).orElseThrow();
        why.setTitre(existingWhy.getTitre());
        why.setDesc(existingWhy.getDesc());
        why.setImageUrl(existingWhy.getImageUrl());
        return whyRepository.save(existingWhy);    }

    @Override
    public void removeWhy(ObjectId id) {
        whyRepository.deleteById(id);
    }
}
