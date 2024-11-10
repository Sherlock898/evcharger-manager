package com.noder.cargadorws.ocpp.messages;

import java.util.Date;

import com.noder.cargadorws.ocpp.messages.types.MeterValue;

public record StopTransactionReq(String idToken, Integer meterStop, Date timestamp, Integer transactionId, Reason reason, MeterValue[] transactionData) {
    public enum Reason{
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
    };
}
