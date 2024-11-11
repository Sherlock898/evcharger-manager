package com.noder.cargadorws.ocpp.messages.version_16;

public record UnlockConnectorConf(UnlockStatus status) {
    public enum UnlockStatus {
        Unlocked,
        UnlockFailed,
        NotSupported
    }
}