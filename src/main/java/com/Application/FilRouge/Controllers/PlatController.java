package com.Application.FilRouge.Controllers;

import com.Application.FilRouge.DTO.PlatsDto;
import com.Application.FilRouge.Services.PlatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plats")
@CrossOrigin(origins = "http://localhost:4200")  // Add CORS configuration
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