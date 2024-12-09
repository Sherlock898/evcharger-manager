package com.noder.chargerCentralApi.models;

import java.time.Instant;
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
import jakarta.validation.constraints.NotNull;

@Entity
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
    private User administrator;

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

    public Integer getConector_count() {
        return conector_count;
    }

    public void setConector_count(Integer conector_count) {
        this.conector_count = conector_count;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ChargerStatus getStatus() {
        return status;
    }

    public void setStatus(ChargerStatus status) {
        this.status = status;
    }

    public List<Connector> getConnectors() {
        return connectors;
    }

    public void setConnectors(List<Connector> connectors) {
        this.connectors = connectors;
    }

    public User getAdministrator() {
        return administrator;
    }

    public void setAdministrator(User administrator) {
        this.administrator = administrator;
    }

    public Instant getCreated_at() {
        return created_at;
    }

    public Instant getUpdated_at() {
        return updated_at;
    }
}