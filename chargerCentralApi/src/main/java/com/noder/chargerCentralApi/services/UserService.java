package com.noder.chargerCentralApi.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final BCryptPasswordEncoder encoder;

    public UserService() {
        this.encoder = new BCryptPasswordEncoder();
    }

    public String hashPin(String rawPin) {
        return encoder.encode(rawPin);
    }

    public boolean isPinValid(String rawPin, String hashedPin) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(rawPin, hashedPin);
    }
}
