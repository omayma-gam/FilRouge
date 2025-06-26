package com.Application.FilRouge.Controllers;

import com.Application.FilRouge.DTO.UserDto;
import com.Application.FilRouge.Services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

      @PostMapping("/add")
 public UserDto addUser(@RequestBody UserDto utilisateursDto){
       return userService.ajouteruser(utilisateursDto);
  }

    @GetMapping("/listUser")
    public List<UserDto> getAllUser(){
        return userService.listeUtilisateur();
    }


    @PutMapping("/update/{id}")
    public UserDto updatUser(@PathVariable Long id , @RequestBody UserDto annonceDto) {
        return userService.modifierUser(id,annonceDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id ) {
        userService.supprimerUser(id);
    }
}
