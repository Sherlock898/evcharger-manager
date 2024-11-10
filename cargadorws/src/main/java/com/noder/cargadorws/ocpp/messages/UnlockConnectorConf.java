package com.noder.cargadorws.ocpp.messages;

public record UnlockConnectorConf(UnlockStatus status) {
    public enum UnlockStatus{
        Unlocked,
        UnlockFailed,
        NotSupported
    };
}