package com.noder.chargerCentralApi.controllers;

import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noder.chargerCentralApi.models.Charger;
import com.noder.chargerCentralApi.models.UserEntity;
import com.noder.chargerCentralApi.services.ChargerService;
import com.noder.chargerCentralApi.services.TransactionService;
import com.noder.chargerCentralApi.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final UserService userService;
    private final ChargerService chargerService;
    private final TransactionService transactionService;

    public AdminController(UserService userService, ChargerService chargerService, TransactionService transactionService) {
        this.userService = userService;
        this.chargerService = chargerService;
        this.transactionService = transactionService;
    }

    // Get all chargers belonging to a specific admin
    @GetMapping("/chargers")
    public ResponseEntity<List<Charger>> getChargers() {
        Long userId = userService.getUserIdFromAuthentication().orElse(null);
        if (userId == null) {
            return ResponseEntity.badRequest().build();
        }
        List<Charger> chargers = chargerService.getChargersFromUser(userId);
        return ResponseEntity.ok(chargers);
    }

    // Register a new charger
    @PostMapping("/chargers")
    public ResponseEntity<Charger> registerCharger(Charger charger) {
        UserEntity user = userService.getUserFromAuthentication().orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        charger.setAdministrator(user);
        return ResponseEntity.ok(chargerService.saveCharger(charger).get());
    }

}
