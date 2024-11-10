package com.noder.cargadorws.ocpp.messages;

public record TriggerMessageReq(MessageTrigger requestedMessage, Integer connectorId){
    public enum MessageTrigger{
        BootNotification,
        DiagnosticsStatusNotification,
        FirmwareStatusNotification,
        Heartbeat,
        MeterValues,
        StatusNotification
    };
}
