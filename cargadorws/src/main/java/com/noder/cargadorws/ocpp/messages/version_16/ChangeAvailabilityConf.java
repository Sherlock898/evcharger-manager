package com.noder.cargadorws.ocpp.messages;

public record ChangeAvailabilityConf(Status status) {
    public enum Status {
        Accepted,
        Rejected,
        Scheduled
    }
}
