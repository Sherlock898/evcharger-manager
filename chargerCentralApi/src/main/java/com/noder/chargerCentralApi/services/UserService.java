package com.noder.chargerCentralApi.services;

import java.util.Collections;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.noder.chargerCentralApi.dtos.UserCreationDTO;
import com.noder.chargerCentralApi.dtos.UserDTO;
import com.noder.chargerCentralApi.models.UserEntity;
import com.noder.chargerCentralApi.repositories.RoleRepostiory;
import com.noder.chargerCentralApi.repositories.UserRepository;

@Service
public class UserService {
    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;
    private final RoleRepostiory roleRepostiory;

    public UserService(UserRepository userRepository, RoleRepostiory roleRepostiory) {
        this.encoder = new BCryptPasswordEncoder();
        this.userRepository = userRepository;
        this.roleRepostiory = roleRepostiory;
    }

    public UserEntity saveUser(UserCreationDTO userDTO) {
        UserEntity user = new UserEntity();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setPin(hashPin(userDTO.getPin()));
        // TODO: do more role stuff
        user.setRoles(Collections.singletonList(roleRepostiory.findByName("USER")));
        return userRepository.save(user);
    }

    public UserDTO toUserDTO(UserEntity user) {
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

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
