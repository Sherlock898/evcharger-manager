package com.noder.chargerCentralApi.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ChargerClient")
public class ChargerClient extends RegisteredUser {
    @OneToMany(mappedBy = "client")
    private List<Card> cards;
}
