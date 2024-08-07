package com.example.backdsdm.Controller;

import com.example.backdsdm.Repositories.PresProjetRepository;
import com.example.backdsdm.services.IPresProjetService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;

import com.example.backdsdm.entities.PresProjet;

@RestController
@RequestMapping("dsdm")
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200" )
public class PresProjetController {
    private final PresProjetRepository dsdmRepository;
    @Autowired
    private IPresProjetService iPresProjetService;
   @PostMapping("add/{id_project}")
   public PresProjet addDsdm(
           @PathVariable String id_project,
           @RequestParam String context,
           @RequestParam String priorisation,
           @RequestParam String status,
           @RequestParam LocalDate startDate,
           @RequestParam LocalDate endDate,
           @RequestParam String id_user
   ) throws IOException {
       PresProjet dsdm = new PresProjet();
       dsdm.setContext(context);
       dsdm.setPriorisation(priorisation);
       dsdm.setStatus(status);
       dsdm.setStartDate(startDate);
       dsdm.setEndDate(endDate);
       dsdm.setIdproject(id_project);
       dsdm.setId_user(id_user);
       return iPresProjetService.addDsdm(dsdm);
   }
    @GetMapping("/show/{id_project}")
    public PresProjet getDsdmByProjectId(@PathVariable String id_project) {
        return iPresProjetService.getDsdmByProjectId(id_project);
    }
    @PutMapping("/update/{idproject}/{id}")
    public ResponseEntity<PresProjet> updateDsdm(
            @PathVariable String idproject,
            @PathVariable String id,
            @RequestParam String context,
            @RequestParam String priorisation,
            @RequestParam String status,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam String id_user) {
        PresProjet updatedDsdm = iPresProjetService.updateDsdm(idproject, id, context, priorisation, status, startDate, endDate, id_user);
        return ResponseEntity.ok(updatedDsdm);
    }
    @GetMapping("showdsdm/{id}")
    public PresProjet retrieveDSdm(@PathVariable ObjectId id){
        return iPresProjetService.retrieveDSdm(id);
    }
}
