package com.example.backdsdm.Controller;

import com.example.backdsdm.Repositories.WhyRepository;
import com.example.backdsdm.entities.Why;
import com.example.backdsdm.services.IWhyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("why")
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200" )
public class WhyController {
    private final WhyRepository whyRepository;
    @Autowired
    private IWhyService iWhyService;
    @PostMapping("add")
    public Why addWhy(
            @RequestParam String titre,
            @RequestParam String desc,
            @RequestParam("imageUrl") MultipartFile imageUrl) throws IOException {
        Why why = new Why();
        why.setTitre(titre);
        why.setDesc(desc);
        why.setImageUrl(imageUrl.getBytes());
        return iWhyService.addWhy(why);
    }
    @GetMapping("show/{id}")
    public Why retrieveWhy(@PathVariable ObjectId id){
        return iWhyService.retrieveWhy(id);
    }
    @GetMapping("showall")
    public List<Why> retrieveWhys(){
        return iWhyService.retrieveWhys();
    }
    @PutMapping("update")
    public Why updateWhy(@RequestParam ObjectId id,
                                @RequestParam String titre,
                                @RequestParam String desc,
                                @RequestParam("imageUrl") MultipartFile imageUrl )throws IOException{
        Why existingWhy = whyRepository.findById(id).orElseThrow();

        existingWhy.setTitre(titre);
        existingWhy.setDesc(desc);
        existingWhy.setImageUrl(imageUrl.getBytes());
        return iWhyService.updateWhy(id, existingWhy);
    }

    @DeleteMapping("delete/{id}")
    public void removeWhy(@PathVariable ObjectId id){
        iWhyService.removeWhy(id);
    }

}
