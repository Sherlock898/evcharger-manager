package com.noder.cargadorws.ocpp.messages.version_16;
import java.util.Date;

public record BootNotificationConf(Date currentTime, Integer interval, Status status) {
    public enum Status {
        Accepted,
        Pending,
        Rejected
    }
    
}