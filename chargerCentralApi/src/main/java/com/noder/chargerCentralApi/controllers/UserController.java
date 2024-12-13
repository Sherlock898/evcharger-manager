package com.noder.chargerCentralApi.controllers;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.noder.chargerCentralApi.dtos.UserCreationDTO;
import com.noder.chargerCentralApi.dtos.UserDTO;
import com.noder.chargerCentralApi.models.UserEntity;
import com.noder.chargerCentralApi.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public UserDTO createUser(@RequestBody @Valid UserCreationDTO userCreationDTO) {
        User user = userService.saveUser(userCreationDTO);
        return userService.toUserDTO(user);
    }

    @PostMapping("/validate-pin")
    public String validatePin(@RequestParam String rawPin, @RequestParam String hashedPin) {
        boolean isValid = userService.isPinValid(rawPin, hashedPin);
        return isValid ? "Valid pin" : "Invalid pin";
    }
}