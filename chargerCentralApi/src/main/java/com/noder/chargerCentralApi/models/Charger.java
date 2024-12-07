package com.noder.chargerCentralApi.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Charger")
public class Charger {
    public enum ChargerStatus {
        AVAILABLE,
        UNAVAILABLE,
        FAULTED
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location;
    @NotNull
    private Integer conector_count;
    @NotNull
    private String uri;
    @NotNull
    private String key;
    @NotNull
    private ChargerStatus status;

    @OneToMany(mappedBy = "charger")
    private List<Connector> connectors;

    @ManyToOne
    @JoinColumn(name = "administrator_id")
    private ChargerAdministrator administrator;

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