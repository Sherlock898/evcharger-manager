package com.noder.cargadorws.ocpp.messages;

import java.time.Instant;

import com.noder.cargadorws.ocpp.messages.types.MeterValue;

public record StopTransactionReq(String idToken, Integer meterStop, Instant timestamp, Integer transactionId, Reason reason, MeterValue[] transactionData) {
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
