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

// This class is pending, first we need to implement a way to identify the users.
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
}