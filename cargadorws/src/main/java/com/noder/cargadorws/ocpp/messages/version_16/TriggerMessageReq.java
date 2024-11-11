package com.noder.cargadorws.ocpp.messages.version_16;

public record TriggerMessageReq(MessageTrigger requestedMessage, Integer connectorId){
    public enum MessageTrigger {
        BootNotification,
        DiagnosticsStatusNotification,
        FirmwareStatusNotification,
        Heartbeat,
        MeterValues,
        StatusNotification
    }
}
