package com.noder.chargerCentralApi.controllers;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.noder.chargerCentralApi.models.User;
import com.noder.chargerCentralApi.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public String createUser(@RequestBody User user, @RequestParam String rawPin) {
        String hashedPin = userService.hashPin(rawPin);
        user.setPin(hashedPin);
        userService.saveUser(user);
        return "User created successfully";
    }

    @PostMapping("/validate-pin")
    public String validatePin(@RequestParam String rawPin, @RequestParam String hashedPin) {
        boolean isValid = userService.isPinValid(rawPin, hashedPin);
        return isValid ? "Valid pin" : "Invalid pin";
    }
}