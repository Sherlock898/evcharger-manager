package com.noder.cargadorws.ocpp.messages;


public record ChangeAvailabilityReq(Integer connectorId, AvailabilityType type) {
    public enum AvailabilityType {
        Inoperative,
        Operative
    }
}

