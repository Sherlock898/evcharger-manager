package com.noder.cargadorws.ocpp.messages;

public record ResetConf(ResetStatus status) {
    public enum ResetStatus {
        Accepted,
        Rejected
    }
}
