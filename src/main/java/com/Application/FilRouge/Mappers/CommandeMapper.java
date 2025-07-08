package com.Application.FilRouge.Mappers;

import com.Application.FilRouge.DTO.CommandeDto;
import com.Application.FilRouge.DTO.PlatsDto;
import com.Application.FilRouge.Model.Commande;
import com.Application.FilRouge.Model.Plats;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface CommandeMapper {
    CommandeDto commandeToDto(Commande commande);
    Commande dtoToCommande(CommandeDto commandeDto);

}
