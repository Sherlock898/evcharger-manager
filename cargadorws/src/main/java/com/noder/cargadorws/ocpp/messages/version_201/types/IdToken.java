package com.noder.cargadorws.ocpp.messages.version_201.types;

public record IdToken(String idToken, Type type) {
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
