package com.noder.cargadorws.ocpp.messages.version_201.types;

public record ClearMonitoringResult(Status status, Integer id, StatusInfo statusInfo) {
    public enum Status {
        Accepted,
        Rejected,
        NotFound
    }
}
