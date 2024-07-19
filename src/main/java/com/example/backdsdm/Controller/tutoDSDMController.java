package com.example.backdsdm.Controller;

import com.example.backdsdm.services.ITutoDSDMService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.backdsdm.entities.tutoDSDM;

@RestController
@RequestMapping("tutodsdm")
@Slf4j
@AllArgsConstructor
public class tutoDSDMController {
    @Autowired
    private ITutoDSDMService iTutoDSDMService;

    @PostMapping("add")
    public tutoDSDM addTutoDsdm(@RequestBody tutoDSDM t){

        return iTutoDSDMService.addTutoDsdm(t);
    }
    @GetMapping("show/{idTuto}")
    public tutoDSDM retrieveTutoDSDM(@PathVariable ObjectId idTuto){
        return iTutoDSDMService.retrieveTutoDSDM(idTuto);
    }

    @PutMapping("update")
    public tutoDSDM updateTutoDsdm(@RequestBody tutoDSDM t){
        return iTutoDSDMService.updateTutoDsdm(t);
    }
    @DeleteMapping("delete/{idTuto}")
    public void removeTutoDsdm(@PathVariable ObjectId idTuto){
        iTutoDSDMService.removeTutoDsdm(idTuto);
    }


}
