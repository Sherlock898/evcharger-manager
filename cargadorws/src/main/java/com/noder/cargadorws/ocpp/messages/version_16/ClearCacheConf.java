package com.noder.cargadorws.ocpp.messages;

public record ClearCacheConf(Status status) {
    public enum Status {
        Accepted,
        Rejected
    }
}
