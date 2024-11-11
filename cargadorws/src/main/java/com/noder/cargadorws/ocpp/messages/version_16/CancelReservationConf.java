package com.noder.cargadorws.ocpp.messages.version_16;

public record CancelReservationConf(Status status) {
    public enum Status {
        Accepted,
        Rejected
    }
}
