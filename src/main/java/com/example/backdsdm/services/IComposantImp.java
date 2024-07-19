package com.example.backdsdm.services;

import com.example.backdsdm.Repositories.ComposantRepository;
import com.example.backdsdm.entities.Composant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class IComposantImp implements IComposantService{
    private final ComposantRepository composantRepository;

    @Override
    public Composant addComposant(Composant composant) {
        return composantRepository.save(composant);
    }

    @Override
    public Composant updateComposant(ObjectId m, Composant composant) {
        Composant existingComposant = composantRepository.findById(m).orElseThrow();
        existingComposant.setNomComposant(composant.getNomComposant());
        existingComposant.setTitre(composant.getTitre());
        existingComposant.setDesc(composant.getDesc());
        existingComposant.setImageUrl(composant.getImageUrl());
            return composantRepository.save(existingComposant);
    }

    @Override
    public Composant retrieveComposant(ObjectId idComposant) {
        return composantRepository.findById(idComposant).orElseThrow(()->new IllegalArgumentException("No composant found with this id"));
    }

    @Override
    public void removeComposant(ObjectId idComposant) {
        composantRepository.deleteById(idComposant);

    }

    @Override
    public List<Composant> retrieveComposants() {
        return (List<Composant>) composantRepository.findAll();
    }
}
