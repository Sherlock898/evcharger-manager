package com.noder.cargadorws.ocpp.messages;
import java.util.Date;

import com.noder.cargadorws.ocpp.messages.types.ChargingSchedule;

public record GetCompositeScheduleConf(Status status, Integer connectorId, Date scheduleStart, ChargingSchedule chargingSchedule) {
    public enum Status {
        Accepted,
        Rejected
    }
}
