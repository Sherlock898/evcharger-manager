package com.noder.cargadorws.ocpp.messages.version_201;
import com.noder.cargadorws.ocpp.messages.version_201.types.StatusInfo;

public record GetBaseReportResponse(Status status, StatusInfo statusInfo) {
    public enum Status {
        Accepted,
        Rejected,
        NotSupported,
        EmptyResultSet
    }
}
