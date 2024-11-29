package com.noder.chargerCentralApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noder.chargerCentralApi.models.RegisteredUser;

@Repository
public interface UserRepository extends JpaRepository<RegisteredUser, Long> {
}