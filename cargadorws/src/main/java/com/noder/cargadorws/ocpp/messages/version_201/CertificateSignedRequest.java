package com.noder.cargadorws.ocpp.messages.version_201;

public record CertificateSignedRequest(String certificateChain, CertificateType certificateType) {
    public enum CertificateType {
        ChargingStationCertificate,
        V2GCertificate
    }
}
