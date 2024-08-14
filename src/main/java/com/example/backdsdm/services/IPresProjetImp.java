package com.example.backdsdm.services;

import com.example.backdsdm.Repositories.PresProjetRepository;
import com.example.backdsdm.entities.PresProjet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class IPresProjetImp implements IPresProjetService {
    private final PresProjetRepository dsdmRepository;
    @Override
    public PresProjet addDsdm(PresProjet dsdm) {
        return dsdmRepository.save(dsdm);    }


@Override
public PresProjet updateDsdm(String idproject, String id, String context, String priorisation, String status, String startDate, String endDate, String id_user) {
    Optional<PresProjet> optionalDsdm = dsdmRepository.findByIdAndIdproject(id, idproject);

    if (!optionalDsdm.isPresent()) {
        throw new IllegalArgumentException("DSDM not found");
    }

    PresProjet dsdm = optionalDsdm.get();
    dsdm.setContext(context);
    dsdm.setPriorisation(priorisation);
    dsdm.setStatus(status);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    dsdm.setStartDate(LocalDate.parse(startDate, formatter));
    dsdm.setEndDate(LocalDate.parse(endDate, formatter));
    dsdm.setId_user(id_user);

    return dsdmRepository.save(dsdm);
}
    @Override
    public PresProjet getDsdmByProjectId(String idProject) {
        return dsdmRepository.findByIdproject(idProject);
    }
    @Override
    public PresProjet retrieveDSdm(ObjectId id) {
        return dsdmRepository.findById(id).orElseThrow(()->new IllegalArgumentException("No dsdm found with this id"));
    }
}
