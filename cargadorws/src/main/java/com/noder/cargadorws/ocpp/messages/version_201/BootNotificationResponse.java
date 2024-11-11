package com.noder.cargadorws.ocpp.messages.version_201;
import java.util.Date;

import com.noder.cargadorws.ocpp.messages.version_201.types.StatusInfo;

public record BootNotificationResponse(Date currentTime, Integer interval, Status tatus, StatusInfo statusInfo) {
    public enum Status {
        Accepted,
        Pending,
        Rejected
    }
}
