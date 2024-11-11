package com.noder.cargadorws.ocpp.messages;

public record ClearChargingProfileConf(Status status) {
    public enum Status {
        Accepted,
        Unknown
    }
    
}