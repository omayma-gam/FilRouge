package com.Application.FilRouge.Auth;

import com.Application.FilRouge.Config.JwtService;
import com.Application.FilRouge.Model.Admin;
import com.Application.FilRouge.Model.Client;
import com.Application.FilRouge.Model.Restaurateur;
import com.Application.FilRouge.Model.User;
import com.Application.FilRouge.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationService
{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthentificationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }
    public AuthentificationResponse register(RegisterRequest request) {
        User user;

        // Decide which subclass to create based on role
        switch (request.getRole()) {
            case ADMIN -> user = new Admin();
            case RESTAURATEUR -> user = new Restaurateur();
            case CLIENT -> user = new Client();

            default -> throw new IllegalArgumentException("Invalid role: " + request.getRole());
        }

        user.setUsername(request.getName()); // or request.getUsername()
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        userRepository.save(user); // use appropriate repository for the subclass

        String jwtToken = jwtService.generateToken(user);

        AuthentificationResponse response = new AuthentificationResponse();
        response.setToken(jwtToken);
        return response;
    }


    public AuthentificationResponse authenticate(AuthetificationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail());
        String jwtToken = jwtService.generateToken(user);

        AuthentificationResponse response = new AuthentificationResponse();
        response.setToken(jwtToken);
        return response;
    }
}
