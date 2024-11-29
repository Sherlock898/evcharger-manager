package com.noder.chargerCentralApi.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Conector") 
public class Connector {
    public enum ConnectorStatus {
        AVAILABLE,
        PREPARING,
        CHARGING,
        SUSPENDEDEVSE,
        SUSPENDEDEV,
        RESERVED,
        UNAVAILABLE,
        FAULTED
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long connector_id;
    @NotNull
    private ConnectorStatus status;

    @ManyToOne
    private Charger charger;

    @Column(updatable = false)
    private Date created_at;
    private Date updated_at;
    @PrePersist
    protected void onCreate() {
        this.created_at = new Date();
    }
    @PreUpdate
    protected void onUpdate() {
        this.updated_at = new Date();
    }
}