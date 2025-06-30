package com.Application.FilRouge.Controllers;


import com.Application.FilRouge.DTO.PlatsDto;
import com.Application.FilRouge.DTO.RestaurantDto;
import com.Application.FilRouge.Services.PlatService;
import com.Application.FilRouge.Services.RestaurantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plats")
public class PlatController {

    private final PlatService platService;

    public PlatController(PlatService platService) {
        this.platService = platService;
    }

    @PostMapping("/add")
    public PlatsDto addPlats(@RequestBody PlatsDto platsDto){
        return platService.AjouterPlats(platsDto);
    }

    @GetMapping("/list")
    public List<PlatsDto> getAllPlats(){
        return platService.listePlats();
    }


    @PutMapping("/update/{id}")
    public PlatsDto updatPlats(@PathVariable Long id , @RequestBody PlatsDto platsDto) {
        return platService.modifierPlats(id,platsDto);
    }

    @DeleteMapping("/{id}")
    public void deletePlats(@PathVariable Long id ) {
        platService.supprimerPlats(id);
    }
}
