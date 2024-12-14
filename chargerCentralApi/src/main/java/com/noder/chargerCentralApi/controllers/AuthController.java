package com.noder.chargerCentralApi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noder.chargerCentralApi.dtos.AuthResponseDTO;
import com.noder.chargerCentralApi.dtos.UserCreationDTO;
import com.noder.chargerCentralApi.dtos.UserLoginDTO;
import com.noder.chargerCentralApi.models.UserEntity;
import com.noder.chargerCentralApi.repositories.RoleRepostiory;
import com.noder.chargerCentralApi.repositories.UserRepository;
import com.noder.chargerCentralApi.security.JwtGenerator;
import com.noder.chargerCentralApi.services.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserService userService;
    private JwtGenerator jwtGenerator;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody UserLoginDTO userLoginDTO) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail(), userLoginDTO.getPin())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generate(authentication);
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@Valid @RequestBody UserCreationDTO userCreationDTO) {
        if (userService.existsByEmail(userCreationDTO.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        // TODO: Add that certain roles can create certain roles and stuff
        userService.saveUser(userCreationDTO);
        return ResponseEntity.ok("User created successfully");
    }

}
