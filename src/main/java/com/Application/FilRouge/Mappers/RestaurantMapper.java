package com.Application.FilRouge.Mappers;

import com.Application.FilRouge.DTO.RestaurantDto;
import com.Application.FilRouge.Model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface RestaurantMapper {

    RestaurantDto restaurantToDto(Restaurant restaurant);
    Restaurant dtoToRestaurant(RestaurantDto restaurantDto);
}
