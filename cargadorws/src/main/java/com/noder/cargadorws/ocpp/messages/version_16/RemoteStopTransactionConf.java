package com.noder.cargadorws.ocpp.messages;

public record RemoteStopTransactionConf(RemoteStartStopStatus status) {
    public enum RemoteStartStopStatus {
        Accepted,
        Rejected
    }
}
