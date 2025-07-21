package com.Application.FilRouge.Services;

import com.Application.FilRouge.DTO.PlatsDto;
import com.Application.FilRouge.DTO.RestaurantDto;
import com.Application.FilRouge.Mappers.CommandeMapper;
import com.Application.FilRouge.Mappers.PlatMapper;
import com.Application.FilRouge.Mappers.RestaurantMapper;
import com.Application.FilRouge.Model.Plats;
import com.Application.FilRouge.Model.Restaurant;
import com.Application.FilRouge.Repository.PlatRepository;
import com.Application.FilRouge.Repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class PlatService {
    private final PlatRepository platRepository;
    private final PlatMapper platMapper;
    private final CommandeMapper commandeMapper;  // ajout

    public PlatService(PlatRepository platRepository, PlatMapper platMapper, CommandeMapper commandeMapper) {
        this.platRepository = platRepository;
        this.platMapper = platMapper;
        this.commandeMapper = commandeMapper;
    }

    public PlatsDto AjouterPlats(PlatsDto platsDto){
        Plats plats = platMapper.dtoToPlats(platsDto);

        if(platsDto.getCommande() != null){
            plats.setCommande(commandeMapper.dtoToCommande(platsDto.getCommande()));
        }

        Plats plats1 = platRepository.save(plats);
        return platMapper.platsToDto(plats1);
    }

    public List<PlatsDto> listePlats() {
        return platRepository.findAll().stream()
                .map(platMapper::platsToDto)
                .collect(Collectors.toList());
    }

    public PlatsDto modifierPlats(Long id , PlatsDto platsDto){
        Plats plats = platRepository.findById(id).orElseThrow(() -> new RuntimeException("Plat non trouvé"));

        plats.setName(platsDto.getName());
        plats.setDescription(platsDto.getDescription());
        plats.setPrix(platsDto.getPrix());
        plats.setAvailable(platsDto.isAvailable());
        plats.setCategory(platsDto.getCategory());

        if(platsDto.getCommande() != null){
            plats.setCommande(commandeMapper.dtoToCommande(platsDto.getCommande()));
        } else {
            plats.setCommande(null);
        }

        return platMapper.platsToDto(plats);
    }

    public void supprimerPlats(Long id){
        platRepository.deleteById(id);
    }
}
