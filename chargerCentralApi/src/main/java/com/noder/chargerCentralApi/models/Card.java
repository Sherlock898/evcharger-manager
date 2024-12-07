package com.noder.chargerCentralApi.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String encryptedCardNumber;
    private String cardHolderName;
    private String last4Digits;

    public void setEncryptedCardNumber(String encryptedCardNumber) {
        this.encryptedCardNumber = encryptedCardNumber;
    }

    public void setCardHolderName (String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public void setLast4Digits (String last4Digits) {
        this.last4Digits = last4Digits;
    }

    public String getEncryptedCardNumber() {
        return encryptedCardNumber;
    }

    public String getCardHolderName () {
        return cardHolderName;
    }

    public String getLast4Digits () {
        return last4Digits;
    }

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ChargerClient chargingClient;

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
