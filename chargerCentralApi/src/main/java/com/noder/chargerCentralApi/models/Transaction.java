package com.noder.chargerCentralApi.models;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "Transaction") 
public class Transaction {
    public enum TransactionStatus {
        ACTIVE,
        COMPLETED,
        CANCELLED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant start_time;
    private Instant end_time;
    private String start_meter_value;
    private String end_meter_value;
    private TransactionStatus status;

    @ManyToOne
    private Connector connector;

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
}