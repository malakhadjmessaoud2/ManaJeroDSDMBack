package com.example.backdsdm.services;

import com.example.backdsdm.Repositories.AdvantageRepository;
import com.example.backdsdm.Repositories.ComposantRepository;
import com.example.backdsdm.entities.Advantage;
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
public class IAdvantageImp implements IAdvantageService{
    private final AdvantageRepository advantageRepository;

    @Override
    public Advantage addAdv(Advantage advantage) {
        return advantageRepository.save(advantage);    }

    @Override
    public Advantage retrieveAdv(ObjectId id) {
        return advantageRepository.findById(id).orElseThrow(()->new IllegalArgumentException("No advantage found with this id"));
    }

    @Override
    public List<Advantage> retrieveAdvs() {
        return (List<Advantage>) advantageRepository.findAll();    }

    @Override
    public Advantage updateAdv(ObjectId id, Advantage existingAdv) {
        Advantage Adv = advantageRepository.findById(id).orElseThrow();
        Adv.setTitre(existingAdv.getTitre());
        Adv.setDesc(existingAdv.getDesc());
        Adv.setImageUrl(existingAdv.getImageUrl());
        return advantageRepository.save(existingAdv);
            }

    @Override
    public void removeAdv(ObjectId id) {
        advantageRepository.deleteById(id);

    }
}
