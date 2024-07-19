package com.example.backdsdm.Controller;

import com.example.backdsdm.Repositories.AdvantageRepository;
import com.example.backdsdm.Repositories.WhatIfRepository;
import com.example.backdsdm.entities.Advantage;
import com.example.backdsdm.entities.WhatIf;
import com.example.backdsdm.services.IAdvantageService;
import com.example.backdsdm.services.IWhatIfService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("whatif")
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200" )
public class WhatIfController {
    private final WhatIfRepository whatIfRepository;
    @Autowired
    private IWhatIfService iWhatIfService;
    @PostMapping("add")
    public WhatIf addWhatIf(
            @RequestParam String titre,
            @RequestParam String desc,
            @RequestParam("imageUrl") MultipartFile imageUrl) throws IOException {
        WhatIf whatIf = new WhatIf();
        whatIf.setTitre(titre);
        whatIf.setDesc(desc);
        whatIf.setImageUrl(imageUrl.getBytes());
        return iWhatIfService.addWhatIf(whatIf);
    }
    @GetMapping("show/{id}")
    public WhatIf retrieveWhatIf(@PathVariable ObjectId id){
        return iWhatIfService.retrieveWhatIf(id);
    }
    @GetMapping("showall")
    public List<WhatIf> retrieveWhatIfs(){
        return iWhatIfService.retrieveWhatIfs();
    }
    @PutMapping("update")
    public WhatIf updateWhatIf(@RequestParam ObjectId id,
                               @RequestParam String titre,
                               @RequestParam String desc,
                               @RequestParam("imageUrl") MultipartFile imageUrl )throws IOException{
        WhatIf existingWhatIf = whatIfRepository.findById(id).orElseThrow();

        existingWhatIf.setTitre(titre);
        existingWhatIf.setDesc(desc);
        existingWhatIf.setImageUrl(imageUrl.getBytes());
        return iWhatIfService.updateWhatIf(id, existingWhatIf);
    }

    @DeleteMapping("delete/{id}")
    public void removeWhatIf(@PathVariable ObjectId id){
        iWhatIfService.removeWhatIf(id);
    }


}
