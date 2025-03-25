package com.noder.chargerCentralApi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.noder.chargerCentralApi.models.Charger;
import com.noder.chargerCentralApi.models.Transaction;
import com.noder.chargerCentralApi.repositories.ChargerRepository;

@Service
public class ChargerService {
    private final ChargerRepository chargerRepository;

    public ChargerService(ChargerRepository chargerRepository) {
        this.chargerRepository = chargerRepository;
    }

    public Optional<Charger> saveCharger(Charger charger) {
        return Optional.of(chargerRepository.save(charger));
    }

    public Optional<Charger> getCharger(Long chargerId) {
        return chargerRepository.findById(chargerId);
    }
    
    public List<Charger> getChargersFromUser(Long userId) {
        return chargerRepository.findByAdministrator_id(userId);
    }

    public boolean isRegistered(Long chargerId) {
        return chargerRepository.existsById(chargerId);
    }

    public boolean allowConnection(Long chargerId) {
        if (chargerRepository.existsById(chargerId)) {
            return true;
        } else {
            return false;
        }
    }
}
