package com.noder.restapi.models;

import java.time.Instant;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotNull;

@Entity
public class ChargingStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String location;
    @NotNull
    private String name;

    @OneToMany(mappedBy = "chargingStation")
    private List<Charger> chargers;
    
    @OneToMany(mappedBy = "chargingStation")
    private List<UserEntity> administrators;

    @Column(updatable = false)
    private Instant created_at;
    private Instant updated_at;

    @PrePersist
    protected void onCreate() {
        this.created_at = Instant.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        this.updated_at = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
