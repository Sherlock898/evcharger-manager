package com.noder.cargadorws.ocpp.messages;
import java.time.Instant;

import com.noder.cargadorws.ocpp.messages.types.ChargingSchedule;

public record GetCompositeScheduleConf(Status status, Integer connectorId, Instant scheduleStart, ChargingSchedule chargingSchedule) {
    public enum Status {
        Accepted,
        Rejected
    }
}
