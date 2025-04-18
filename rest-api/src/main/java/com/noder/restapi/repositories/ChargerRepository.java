package com.noder.restapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noder.restapi.models.Charger;

@Repository
public interface ChargerRepository extends JpaRepository<Charger, Long> {
    List<Charger> findByAdministrator_id(Long userId);
}
