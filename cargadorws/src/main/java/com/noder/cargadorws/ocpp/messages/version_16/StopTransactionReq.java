package com.noder.cargadorws.ocpp.messages.version_16;
import java.util.Date;

import com.noder.cargadorws.ocpp.messages.version_16.types.MeterValue;

public record StopTransactionReq(String idToken, Integer meterStop, Date timestamp, Integer transactionId, Reason reason, MeterValue[] transactionData) {
    public enum Reason {
        DeAuthorized,
        EmergencyStop,
        EVDisconnected,
        HardReset,
        Local,
        Other,
        PowerLoss,
        Reboot,
        Remote,
        SoftReset,
        UnlockCommand
    }
}
