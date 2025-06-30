package com.Application.FilRouge.Mappers;

import com.Application.FilRouge.DTO.PlatsDto;
import com.Application.FilRouge.Model.Plats;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PlatMapper {
    PlatsDto platsToDto(Plats plats);
    Plats dtoToPlats(PlatsDto platsDto);
}
