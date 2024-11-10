package com.noder.cargadorws.ocpp.messages;

public record CancelReservationConf(Status status) {
    public enum Status {
        Accepted,
        Rejected
    }
}
