package com.noder.cargadorws.ocpp.messages;

public record DataTransferConf(Status status, String data) {
    public enum Status {
        Accepted,
        Rejected,
        UnknownMessageId
    }
}
