package com.noder.cargadorws.ocpp.messages;

public record SendLocalListConf(UpdateStatus status) {
    public enum UpdateStatus {
        Accepted,
        Failed,
        NotSupported,
        VersionMismatch
    }
}
