package com.noder.cargadorws.ocpp.messages;

public record RemoteStartTransactionConf(RemoteStartStopStatus status) {
    public enum RemoteStartStopStatus{
        Accepted,
        Rejected
    };
}
