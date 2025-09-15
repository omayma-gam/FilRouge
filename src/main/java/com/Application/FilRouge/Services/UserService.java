package com.Application.FilRouge.Services;

import com.Application.FilRouge.DTO.UserDto;
import com.Application.FilRouge.Mappers.UserMapper;
import com.Application.FilRouge.Model.User;
import com.Application.FilRouge.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {
    private final UserRepository userRepository;
    private UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto ajouteruser(UserDto utilisateursDto){
     User utilisateurs= userMapper.dtoToUser(utilisateursDto);
     utilisateurs.setPassword(passwordEncoder.encode(utilisateurs.getPassword()));
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
        if (utilisateursDto.getPassword() != null && !utilisateursDto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(utilisateursDto.getPassword()));
        }

        User updatedUser = userRepository.save(user);

        return userMapper.userToDto(updatedUser);
    }

    public void supprimerUser(Long id){
        userRepository.deleteById(id);
    }
}
