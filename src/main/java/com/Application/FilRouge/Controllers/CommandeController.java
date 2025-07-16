package com.Application.FilRouge.Controllers;


import com.Application.FilRouge.DTO.CommandeDto;
import com.Application.FilRouge.Services.CommandeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commande")
public class CommandeController {

    private final CommandeService commandeService;

    public CommandeController(CommandeService commandeService){
        this.commandeService = commandeService;
    }


    @PostMapping("/add")
        public CommandeDto addCommand(@RequestBody CommandeDto commandeDto){
        return commandeService.AjouterCommande(commandeDto);
    }

    @GetMapping("/list")
    public List<CommandeDto> getAllCommand(){
        return commandeService.listeCommande();
    }


    @PutMapping("/update/{id}")
    public CommandeDto updateCommand(@PathVariable Long id , @RequestBody CommandeDto commandeDto) {
        return commandeService.modifierCommande(id,commandeDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCommand(@PathVariable Long id) {
        commandeService.supprimerCommande(id);
    }
}
