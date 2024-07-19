package com.example.backdsdm.Controller;

import com.example.backdsdm.Repositories.ComposantRepository;
import com.example.backdsdm.entities.Composant;
import com.example.backdsdm.entities.tutoDSDM;
import com.example.backdsdm.services.IComposantService;
import com.example.backdsdm.services.ITutoDSDMService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("composant")
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200" )

public class ComposantController {
    private final ComposantRepository composantRepository;

    @Autowired
    private IComposantService iComposantService;
    @PostMapping("add")
    public Composant addComposant(@RequestParam String nomComposant,
                                  @RequestParam String titre,
                                  @RequestParam String desc,
                                  @RequestParam String typeAff,
                                  @RequestParam("imageUrl") MultipartFile imageUrl) throws IOException {
        Composant composant = new Composant();
        composant.setNomComposant(nomComposant);
        composant.setTitre(titre);
        composant.setDesc(desc);
        composant.setTypeAff(typeAff);
        composant.setImageUrl(imageUrl.getBytes());
        return iComposantService.addComposant(composant);
    }
    @GetMapping("show/{idComposant}")
    public Composant retrieveComposant(@PathVariable ObjectId idComposant){
        return iComposantService.retrieveComposant(idComposant);
    }
    @GetMapping("showall")
    public List<Composant> retrieveComposants(){
        return iComposantService.retrieveComposants();
    }

    @PutMapping("update")
    public Composant updateComposant(@RequestParam ObjectId id,@RequestParam String nomComposant,
                                     @RequestParam String titre,
                                     @RequestParam String desc,
                                     @RequestParam String typeAff,
                                     @RequestParam("imageUrl") MultipartFile imageUrl )throws IOException{
        Composant existingComposant = composantRepository.findById(id).orElseThrow();

        existingComposant.setNomComposant(nomComposant);
        existingComposant.setTitre(titre);
        existingComposant.setDesc(desc);
        existingComposant.setTypeAff(typeAff);
        existingComposant.setImageUrl(imageUrl.getBytes());
        return iComposantService.updateComposant(id, existingComposant);
    }



    @DeleteMapping("delete/{idComposant}")
    public void removeComposant(@PathVariable ObjectId idComposant){
        iComposantService.removeComposant(idComposant);
    }
}
