package com.noder.chargerCentralApi.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.noder.chargerCentralApi.dtos.UserCreationDTO;
import com.noder.chargerCentralApi.dtos.UserDTO;
import com.noder.chargerCentralApi.models.User;
import com.noder.chargerCentralApi.repositories.UserRepository;

@Service
public class UserService {
    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.encoder = new BCryptPasswordEncoder();
        this.userRepository = userRepository;
    }

    public User saveUser(UserCreationDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setPin(hashPin(userDTO.getPin()));

        return userRepository.save(user);
    }

    public UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setUpdatedAt(user.getUpdatedAt());

        return userDTO;
    }

    public String hashPin(String rawPin) {
        return encoder.encode(rawPin);
    }

    public boolean isPinValid(String rawPin, String hashedPin) {
        return encoder.matches(rawPin, hashedPin);
    }
}
