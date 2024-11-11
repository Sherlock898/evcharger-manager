package com.noder.cargadorws.ocpp.messages.version_16;

public record TriggerMessageConf(TriggerMessageStatus status) {
    public enum TriggerMessageStatus {
        Accepted,
        Rejected,
        NotImplemented
    }
}
