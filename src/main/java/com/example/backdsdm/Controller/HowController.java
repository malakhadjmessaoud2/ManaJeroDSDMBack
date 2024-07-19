package com.example.backdsdm.Controller;

import com.example.backdsdm.Repositories.AdvantageRepository;
import com.example.backdsdm.Repositories.HowRepository;
import com.example.backdsdm.entities.Advantage;
import com.example.backdsdm.entities.How;
import com.example.backdsdm.services.IAdvantageService;
import com.example.backdsdm.services.IHowService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("how")
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200" )
public class HowController {
    private final HowRepository howRepository;
    @Autowired
    private IHowService iHowService;
    @PostMapping("add")
    public How addHow(
            @RequestParam String titre,
            @RequestParam String desc,
            @RequestParam("imageUrl") MultipartFile imageUrl) throws IOException {
        How how = new How();
        how.setTitre(titre);
        how.setDesc(desc);
        how.setImageUrl(imageUrl.getBytes());
        return iHowService.addHow(how);
    }
    @GetMapping("show/{id}")
    public How retrieveHow(@PathVariable ObjectId id){
        return iHowService.retrieveHow(id);
    }
    @GetMapping("showall")
    public List<How> retrieveHows(){
        return iHowService.retrieveHows();
    }
    @PutMapping("update")
    public How updateHow(@RequestParam ObjectId id,
                               @RequestParam String titre,
                               @RequestParam String desc,
                               @RequestParam("imageUrl") MultipartFile imageUrl )throws IOException{
        How existingHow = howRepository.findById(id).orElseThrow();

        existingHow.setTitre(titre);
        existingHow.setDesc(desc);
        existingHow.setImageUrl(imageUrl.getBytes());
        return iHowService.updateHow(id, existingHow);
    }

    @DeleteMapping("delete/{id}")
    public void removeHow(@PathVariable ObjectId id){
        iHowService.removeHow(id);
    }


}
