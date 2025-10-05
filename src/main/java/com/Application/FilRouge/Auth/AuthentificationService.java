package com.Application.FilRouge.Auth;

import com.Application.FilRouge.Config.JwtService;
import com.Application.FilRouge.Model.Client;
import com.Application.FilRouge.Model.Restaurateur;
import com.Application.FilRouge.Model.User;
import com.Application.FilRouge.Repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthentificationService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthentificationResponse register(RegisterRequest request) {
        User user;

        // Création de l'utilisateur selon son rôle
        switch (request.getRole()) {
            case RESTAURATEUR -> user = new Restaurateur();
            case CLIENT -> user = new Client();
            default -> throw new IllegalArgumentException("Invalid role: " + request.getRole());
        }

        user.setUsername(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);

        return new AuthentificationResponse(jwtToken, user.getId(), user.getUsername(), user.getEmail(), user.getRole());
    }

    public AuthentificationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail());

        if (user == null) {
            throw new IllegalArgumentException("User not found with email: " + request.getEmail());
        }

        String jwtToken = jwtService.generateToken(user);

        return new AuthentificationResponse(jwtToken, user.getId(), user.getUsername(), user.getEmail(), user.getRole());
    }
}
