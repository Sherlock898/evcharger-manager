package com.noder.cargadorws.ocpp.messages.version_201.types;

public record GroupIdToken(String idToken, Type type, AdditionalInfo[] additionalInfo) {
    public enum Type {
        Central,
        eMAID,
        ISO14443,
        ISO15693,
        KeyCode,
        Local,
        MacAddress,
        NoAuthorization
    }
}
