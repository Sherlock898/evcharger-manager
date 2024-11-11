package com.noder.cargadorws.ocpp.messages.version_201;
import com.noder.cargadorws.ocpp.messages.version_201.types.StatusInfo;

public record Get15118EVCertificateResponse(Status status, String exiResponse, StatusInfo statusInfo) {
    public enum Status {
        Accepted,
        Failed
    }
}
