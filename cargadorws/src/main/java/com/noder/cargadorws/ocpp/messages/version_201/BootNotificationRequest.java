package com.noder.cargadorws.ocpp.messages.version_201;
import com.noder.cargadorws.ocpp.messages.version_201.types.ChargingStation;

public record BootNotificationRequest(Reason reason, ChargingStation  chargingStation) {
    public enum Reason {
        ApplicationReset,
        FirmwareUpdate,
        LocalReset,
        PowerUp,
        RemoteReset,
        ScheduledReset,
        Triggered,
        Unknown,
        Watchdog
    }
}
