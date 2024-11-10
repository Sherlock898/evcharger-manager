package com.noder.cargadorws.ocpp.messages;

public record TriggerMessageConf(TriggerMessageStatus status) {
    public enum TriggerMessageStatus{
        Accepted,
        Rejected,
        NotImplemented
    };
}
