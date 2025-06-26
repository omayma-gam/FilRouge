package com.Application.FilRouge.Services;

import com.Application.FilRouge.DTO.UserDto;
import com.Application.FilRouge.Mappers.UserMapper;
import com.Application.FilRouge.Model.User;
import com.Application.FilRouge.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {


    private final UserRepository userRepository;
    private UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto ajouteruser(UserDto utilisateursDto){
     User utilisateurs= userMapper.dtoToUser(utilisateursDto);
     User utilisateurs1=userRepository.save(utilisateurs);
       return userMapper.userToDto(utilisateurs1);
  }

    public List<UserDto> listeUtilisateur() {
        return userRepository.findAll().stream()
                .map(userMapper::userToDto)
                .collect(Collectors.toList());
    }
    public UserDto modifierUser(Long id , UserDto utilisateursDto){
        User user=userRepository.findById(id).orElse(null);

        if(user==null){
            throw new RuntimeException("user not found");
        }
        user.setUsername(utilisateursDto.getUsername());
        user.setEmail(utilisateursDto.getEmail());
        user.setPassword(utilisateursDto.getPassword());

        return userMapper.userToDto(user);
    }

    public void supprimerUser(Long id){
        userRepository.deleteById(id);
    }
}
