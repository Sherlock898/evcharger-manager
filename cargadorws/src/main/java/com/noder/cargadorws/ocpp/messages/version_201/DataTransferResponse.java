package com.noder.cargadorws.ocpp.messages.version_201;
import com.noder.cargadorws.ocpp.messages.version_201.types.StatusInfo;

public record DataTransferResponse(Status status, String data, StatusInfo statusInfo) {
    public enum Status {
        Accepted,
        Rejected,
        UnknownMessageId,
        UnknownVendorId
    }
}
