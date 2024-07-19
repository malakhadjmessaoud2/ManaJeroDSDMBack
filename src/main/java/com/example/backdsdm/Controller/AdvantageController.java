package com.example.backdsdm.Controller;

import com.example.backdsdm.Repositories.AdvantageRepository;
import com.example.backdsdm.Repositories.IntroductionRepository;
import com.example.backdsdm.entities.Advantage;
import com.example.backdsdm.entities.Introduction;
import com.example.backdsdm.services.IAdvantageService;
import com.example.backdsdm.services.IIntroductionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("advantage")
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200" )
public class AdvantageController {
    private final AdvantageRepository advantageRepository;
    @Autowired
    private IAdvantageService iAdvantageService;
    @PostMapping("add")
    public Advantage addAdv(
            @RequestParam String titre,
            @RequestParam String desc,
            @RequestParam("imageUrl") MultipartFile imageUrl) throws IOException {
        Advantage advantage = new Advantage();
        advantage.setTitre(titre);
        advantage.setDesc(desc);
        advantage.setImageUrl(imageUrl.getBytes());
        return iAdvantageService.addAdv(advantage);
    }
    @GetMapping("show/{id}")
    public Advantage retrieveAdv(@PathVariable ObjectId id){
        return iAdvantageService.retrieveAdv(id);
    }
    @GetMapping("showall")
    public List<Advantage> retrieveAdvs(){
        return iAdvantageService.retrieveAdvs();
    }
    @PutMapping("update")
    public Advantage updateAdv(@RequestParam ObjectId id,
                                    @RequestParam String titre,
                                    @RequestParam String desc,
                                    @RequestParam("imageUrl") MultipartFile imageUrl )throws IOException{
        Advantage existingAdv = advantageRepository.findById(id).orElseThrow();

        existingAdv.setTitre(titre);
        existingAdv.setDesc(desc);
        existingAdv.setImageUrl(imageUrl.getBytes());
        return iAdvantageService.updateAdv(id, existingAdv);
    }

    @DeleteMapping("delete/{id}")
    public void removeAdv(@PathVariable ObjectId id){
        iAdvantageService.removeAdv(id);
    }


}
