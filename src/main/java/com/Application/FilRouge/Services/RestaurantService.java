package com.Application.FilRouge.Services;

import com.Application.FilRouge.DTO.RestaurantDto;
import com.Application.FilRouge.DTO.UserDto;
import com.Application.FilRouge.Mappers.RestaurantMapper;
import com.Application.FilRouge.Mappers.UserMapper;
import com.Application.FilRouge.Model.Restaurant;
import com.Application.FilRouge.Model.User;
import com.Application.FilRouge.Repository.RestaurantRepository;
import com.Application.FilRouge.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private RestaurantMapper restaurantMapper;

    public RestaurantService(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
    }

    public RestaurantDto AjouterRestaurant(RestaurantDto restaurantDto){
        Restaurant restaurant=restaurantMapper.dtoToRestaurant(restaurantDto);
        Restaurant restaurant1=restaurantRepository.save(restaurant);
        return restaurantMapper.restaurantToDto(restaurant1);
    }

    public List<RestaurantDto> listeRestaurants() {
        return restaurantRepository.findAll().stream()
                .map(restaurantMapper::restaurantToDto)
                .collect(Collectors.toList());
    }

    public List<RestaurantDto> listRestaurantsName(String name){
        return restaurantRepository.findByName(name).stream()
                .map(restaurantMapper::restaurantToDto)
                .collect(Collectors.toList());
    }

    public RestaurantDto modifierRestaurant(Long id , RestaurantDto restaurantDto){
        Restaurant restaurant=restaurantRepository.findById(id).orElse(null);

        if(restaurant==null){
            throw new RuntimeException("user not found");
        }
        restaurant.setName(restaurantDto.getName());
        restaurant.setAdresse(restaurantDto.getAdresse());
        restaurant.setPhone(restaurantDto.getPhone());
        restaurant.setEmail(restaurantDto.getEmail());
        restaurant.setDescription(restaurantDto.getDescription());
        return restaurantMapper.restaurantToDto(restaurant);
    }

    public void supprimerRestaurant(Long id){
        restaurantRepository.deleteById(id);
    }

}
