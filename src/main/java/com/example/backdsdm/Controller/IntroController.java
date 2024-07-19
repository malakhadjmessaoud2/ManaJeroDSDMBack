package com.example.backdsdm.Controller;

import com.example.backdsdm.Repositories.IntroductionRepository;
import com.example.backdsdm.entities.Composant;
import com.example.backdsdm.entities.Introduction;
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
@RequestMapping("introduction")
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200" )
public class IntroController {
    private final IntroductionRepository introductionRepository;
    @Autowired
    private IIntroductionService iIntroductionService;
    @PostMapping("add")
    public Introduction addintro(
                                  @RequestParam String titre,
                                  @RequestParam String desc,
                                  @RequestParam("imageUrl") MultipartFile imageUrl) throws IOException {
        Introduction introduction = new Introduction();
        introduction.setTitre(titre);
        introduction.setDesc(desc);
        introduction.setImageUrl(imageUrl.getBytes());
        return iIntroductionService.addintro(introduction);
    }
    @GetMapping("show/{idIntro}")
    public Introduction retrieveIntro(@PathVariable ObjectId idIntro){
        return iIntroductionService.retrieveIntro(idIntro);
    }
    @GetMapping("showall")
    public List<Introduction> retrieveIntros(){
        return iIntroductionService.retrieveIntros();
    }

    @PutMapping("update")
    public Introduction updateIntro(@RequestParam ObjectId id,
                                     @RequestParam String titre,
                                     @RequestParam String desc,
                                     @RequestParam("imageUrl") MultipartFile imageUrl )throws IOException{
        Introduction existingIntro = introductionRepository.findById(id).orElseThrow();

        existingIntro.setTitre(titre);
        existingIntro.setDesc(desc);
        existingIntro.setImageUrl(imageUrl.getBytes());
        return iIntroductionService.updateIntro(id, existingIntro);
    }




    @DeleteMapping("delete/{idinto}")
    public void removeIntro(@PathVariable ObjectId idinto){
        iIntroductionService.removeIntro(idinto);
    }


}
