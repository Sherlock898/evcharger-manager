package com.noder.chargerCentralApi.models;

import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class GeneralAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private int phone;
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
