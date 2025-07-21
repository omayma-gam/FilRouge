package com.Application.FilRouge.Services;

import com.Application.FilRouge.DTO.CommandeDto;
import com.Application.FilRouge.Mappers.CommandeMapper;
import com.Application.FilRouge.Model.Commande;
import com.Application.FilRouge.Repository.CommandeRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;
@Service
public class CommandeService {

    private final CommandeRepository commandeRepository;
    private CommandeMapper commandeMapper;

    public CommandeService(CommandeRepository commandeRepository, CommandeMapper commandeMapper) {
        this.commandeRepository = commandeRepository;
        this.commandeMapper = commandeMapper;
    }

    public CommandeDto AjouterCommande(CommandeDto commandeDto){
        Commande commande=commandeMapper.dtoToCommande(commandeDto);
        Commande commande1=commandeRepository.save(commande);
        return commandeMapper.commandeToDto(commande1);
    }

    public List<CommandeDto> listeCommande() {
        return commandeRepository.findAll().stream()
                .map(commandeMapper::commandeToDto)
                .collect(Collectors.toList());
    }

    public CommandeDto modifierCommande(Long id , CommandeDto commandeDto){
        Commande commande=commandeRepository.findById(id).orElse(null);

        if(commande==null){
            throw new RuntimeException("user not found");
        }
        commande.getDatedecommande(commandeDto.getDatedecommande());
        commande.setMontant_total(commandeDto.getMontant_total());
        commande.setStatus(commandeDto.getStatus());
        commande.setLivraisonType(commandeDto.getLivraisonType());

        return commandeMapper.commandeToDto(commande);
    }

    public void supprimerCommande(Long id){
        commandeRepository.deleteById(id);
    }
}
