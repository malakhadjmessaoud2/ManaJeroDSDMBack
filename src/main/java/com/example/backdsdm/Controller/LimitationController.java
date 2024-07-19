package com.example.backdsdm.Controller;

import com.example.backdsdm.Repositories.AdvantageRepository;
import com.example.backdsdm.Repositories.LimitationRepository;
import com.example.backdsdm.entities.Advantage;
import com.example.backdsdm.entities.Limitation;
import com.example.backdsdm.services.IAdvantageService;
import com.example.backdsdm.services.ILimitationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("limitation")
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200" )
public class LimitationController {
    private final LimitationRepository limitationRepository;
    @Autowired
    private ILimitationService iLimitationService;
    @PostMapping("add")
    public Limitation addLim(
            @RequestParam String titre,
            @RequestParam String desc,
            @RequestParam("imageUrl") MultipartFile imageUrl) throws IOException {
        Limitation limitation = new Limitation();
        limitation.setTitre(titre);
        limitation.setDesc(desc);
        limitation.setImageUrl(imageUrl.getBytes());
        return iLimitationService.addLim(limitation);
    }
    @GetMapping("show/{id}")
    public Limitation retrieveLim(@PathVariable ObjectId id){
        return iLimitationService.retrieveLim(id);
    }
    @GetMapping("showall")
    public List<Limitation> retrieveLims(){
        return iLimitationService.retrieveLims();
    }
    @PutMapping("update")
    public Limitation updateLim(@RequestParam ObjectId id,
                               @RequestParam String titre,
                               @RequestParam String desc,
                               @RequestParam("imageUrl") MultipartFile imageUrl )throws IOException{
        Limitation existingLim = limitationRepository.findById(id).orElseThrow();

        existingLim.setTitre(titre);
        existingLim.setDesc(desc);
        existingLim.setImageUrl(imageUrl.getBytes());
        return iLimitationService.updateLim(id, existingLim);
    }

    @DeleteMapping("delete/{id}")
    public void removeLim(@PathVariable ObjectId id){
        iLimitationService.removeLim(id);
    }


}
