package com.noder.chargerCentralApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noder.chargerCentralApi.models.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
}
