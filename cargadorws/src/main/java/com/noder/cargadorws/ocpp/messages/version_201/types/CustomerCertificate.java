package com.noder.cargadorws.ocpp.messages.version_201.types;

public record CustomerCertificate(HashAlgorithm hashAlgorithm, String issuerNameHash, String issuerKeyHash, String serialNumber) {
    public enum HashAlgorithm {
        SHA256,
        SHA384,
        SHA512
    }
}
