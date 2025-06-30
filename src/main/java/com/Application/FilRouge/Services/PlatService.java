package com.Application.FilRouge.Services;

import com.Application.FilRouge.DTO.PlatsDto;
import com.Application.FilRouge.DTO.RestaurantDto;
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
    private PlatMapper platMapper;

    public PlatService(PlatRepository platRepository, PlatMapper platMapper) {
        this.platRepository = platRepository;
        this.platMapper = platMapper;
    }

    public PlatsDto AjouterPlats(PlatsDto platsDto){
        Plats plats=platMapper.dtoToPlats(platsDto);
        Plats plats1=platRepository.save(plats);
        return platMapper.platsToDto(plats1);
    }

    public List<PlatsDto> listePlats() {
        return platRepository.findAll().stream()
                .map(platMapper::platsToDto)
                .collect(Collectors.toList());
    }

    public PlatsDto modifierPlats(Long id , PlatsDto platsDto){
        Plats plats=platRepository.findById(id).orElse(null);

        if(plats==null){
            throw new RuntimeException("user not found");
        }
        plats.setName(platsDto.getName());
        plats.setDescription(platsDto.getDescription());
        plats.setPrix(platsDto.getPrix());
        plats.setAvailable(platsDto.isAvailable());
        plats.setCategory(platsDto.getCategory());
        plats.setCommande(platsDto.getCommande());
        return platMapper.platsToDto(plats);
    }

    public void supprimerPlats(Long id){
        platRepository.deleteById(id);
    }

}
