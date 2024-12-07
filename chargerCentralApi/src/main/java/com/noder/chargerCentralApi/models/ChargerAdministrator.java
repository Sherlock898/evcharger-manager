package com.noder.chargerCentralApi.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ChargerAdministrator")
public class ChargerAdministrator extends RegisteredUser {
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AdminType adminType;

    public enum AdminType {
        GENERAL, SPECIFIC
    }

    @OneToMany(mappedBy = "administrator")
    private List<Charger> chargers;
}
