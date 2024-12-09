package com.noder.chargerCentralApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.noder.chargerCentralApi.models.User;
import com.noder.chargerCentralApi.repositories.UserRepository;

@Service
public class UserService {
    private final BCryptPasswordEncoder encoder;
    @Autowired
    private UserRepository userRepository;

    public UserService() {
        this.encoder = new BCryptPasswordEncoder();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public String hashPin(String rawPin) {
        return encoder.encode(rawPin);
    }

    public boolean isPinValid(String rawPin, String hashedPin) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(rawPin, hashedPin);
    }
}
