package com.Application.FilRouge.Controllers;


import com.Application.FilRouge.DTO.RestaurantDto;
import com.Application.FilRouge.Services.RestaurantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/add")
    public RestaurantDto addRestaurant(@RequestBody RestaurantDto restaurantDto){
        return restaurantService.AjouterRestaurant(restaurantDto);
    }

    @GetMapping("/listRestaurant")
    public List<RestaurantDto> getAllRestaurant(){
        return restaurantService.listeRestaurants();
    }
   @GetMapping("/listeRestaurantsName")
   public List<RestaurantDto> fetRestaurantsName(@RequestParam String name){
        return restaurantService.listRestaurantsName(name);
   }

    @PutMapping("/update/{id}")
    public RestaurantDto updatRestaurant(@PathVariable Long id , @RequestBody RestaurantDto restaurantDto) {
        return restaurantService.modifierRestaurant(id,restaurantDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id ) {
        restaurantService.supprimerRestaurant(id);
    }
}
