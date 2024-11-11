package com.noder.cargadorws.ocpp.messages.version_201.types;

public record OcspRequestData(HashAlgorithm hashAlgorithm, String issuerNameHash, String issuerKeyHash, String serialNumber, String responderURL) {
    public enum HashAlgorithm {
        SHA256,
        SHA384,
        SHA512
    }
}
