package com.noder.chargerCentralApi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "SpecificAdministrator")
public class SpecificAdministrator extends ChargerAdministrator {
    private String chargerArea;
}