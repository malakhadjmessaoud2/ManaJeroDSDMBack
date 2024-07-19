package com.example.backdsdm.services;

import com.example.backdsdm.Repositories.AdvantageRepository;
import com.example.backdsdm.Repositories.WhatIfRepository;
import com.example.backdsdm.entities.Advantage;
import com.example.backdsdm.entities.WhatIf;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class IWhatIfImp implements IWhatIfService{
    private final WhatIfRepository whatIfRepository;

    @Override
    public WhatIf addWhatIf(WhatIf whatIf) {
        return whatIfRepository.save(whatIf);
    }

    @Override
    public WhatIf retrieveWhatIf(ObjectId id) {
        return whatIfRepository.findById(id).orElseThrow(()->new IllegalArgumentException("No What If found with this id"));
    }

    @Override
    public List<WhatIf> retrieveWhatIfs() {
        return (List<WhatIf>) whatIfRepository.findAll();    }

    @Override
    public WhatIf updateWhatIf(ObjectId id, WhatIf existingWhatIf) {
        WhatIf whatIf = whatIfRepository.findById(id).orElseThrow();
        whatIf.setTitre(existingWhatIf.getTitre());
        whatIf.setDesc(existingWhatIf.getDesc());
        whatIf.setImageUrl(existingWhatIf.getImageUrl());
        return whatIfRepository.save(existingWhatIf);    }

    @Override
    public void removeWhatIf(ObjectId id) {
        whatIfRepository.deleteById(id);

    }
}
