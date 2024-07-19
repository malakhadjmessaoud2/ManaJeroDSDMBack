package com.example.backdsdm.services;

import com.example.backdsdm.Repositories.tutoDSDMRepository;
import com.example.backdsdm.entities.tutoDSDM;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ITutoDSDMServiceImp implements ITutoDSDMService {
 private final tutoDSDMRepository tutoDSDMRepository;
    @Override
    public tutoDSDM addTutoDsdm(tutoDSDM t) {
        return tutoDSDMRepository.save(t);
    }
    @Override
    public tutoDSDM updateTutoDsdm(tutoDSDM t) {
        return tutoDSDMRepository.save(t);
    }



    @Override
    public tutoDSDM retrieveTutoDSDM(ObjectId idTuto) {
        return tutoDSDMRepository.findById(idTuto).orElseThrow(()->new IllegalArgumentException("No tuto DSDM found with this id"));

    }
    @Override
    public void removeTutoDsdm(ObjectId idTuto) {
        tutoDSDMRepository.deleteById(idTuto);

    }
}
