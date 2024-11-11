package com.noder.cargadorws.ocpp.messages.version_16;

import java.util.Date;

public record StatusNotificationReq(Integer connectorId, ChargePointErrorCode errorCode, String info, ChargePointStatus status, Date dateTime, String vendorId, String vendorErrorCode) {
    public enum ChargePointErrorCode {
        ConnectorLockFailure,
        EVCommunicationError,
        GroundFailure,
        HighTemperature,
        InternalError,
        LocalListConflict,
        NoError,
        OtherError,
        OverCurrentFailure,
        OverVoltage,
        PowerMeterFailure,
        PowerSwitchFailure,
        ReaderFailure,
        ResetFailure,
        UnderVoltage,
        WeakSignal,
    }
    
    public enum ChargePointStatus {
        Available,
        Preparing,
        Charging,
        SuspendedEVSE,
        SuspendedEV,
        Finishing,
        Reserved,
        Unavailable,
        Faulted
    }
}
