package com.noder.cargadorws.ocpp.messages.version_201;
import com.noder.cargadorws.ocpp.messages.version_201.types.Evse;

public record ChangeAvailabilityRequest(OperationalStatus operationalStatus, Evse evse) {
    public enum OperationalStatus {
        Inoperative,
        Operative
    }
}
