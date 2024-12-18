package com.noder.chargerCentralApi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.noder.chargerCentralApi.models.Charger;
import com.noder.chargerCentralApi.repositories.ChargerRepository;

@Service
public class ChargerService {
    private final ChargerRepository chargeRepository;

    public ChargerService(ChargerRepository chargeRepository) {
        this.chargeRepository = chargeRepository;
    }

    public Optional<Charger> saveCharger(Charger charger) {
        return Optional.of(chargeRepository.save(charger));
    }

    public Optional<Charger> getCharger(Long chargerId) {
        return chargeRepository.findById(chargerId);
    }
    
    public List<Charger> getChargersFromUser(Long userId) {
        return chargeRepository.findByUserId(userId);
    }

    public boolean isRegistered(Long chargerId) {
        return chargeRepository.existsById(chargerId);
    }

}
