package com.noder.cargadorws.ocpp.messages;


public record ChangeAvailabilityReq(Integer connectorId, Type type) {
    public enum Type {
        Inoperative,
        Operative
    }
}

