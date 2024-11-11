package com.noder.cargadorws.ocpp.messages;

public record ResetReq(ResetType type) {
    public enum ResetType {
        Hard,
        Soft
    }
}
