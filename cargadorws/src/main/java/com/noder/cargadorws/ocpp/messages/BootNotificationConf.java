package com.noder.cargadorws.ocpp.messages;
import java.util.Date;

public record BootNotificationConf(Date currentTime, Integer interval, Status status) {
    public enum Status {
        Accepted,
        Pending,
        Rejected
    }
    
}