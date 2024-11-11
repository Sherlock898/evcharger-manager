package com.noder.cargadorws.ocpp.messages.version_201;

public record Get15118EVCertificateRequest(String iso15118SchemaVersion, Action action, String exiRequest) {
    public enum Action {
        Install,
        Update
    }
}
