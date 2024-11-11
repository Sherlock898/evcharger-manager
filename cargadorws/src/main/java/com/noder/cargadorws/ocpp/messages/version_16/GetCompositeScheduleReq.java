package com.noder.cargadorws.ocpp.messages;

public record GetCompositeScheduleReq(Integer  connectorId, Integer duration, ChargingRateUnit chargingRateUnit) {
    public enum ChargingRateUnit {
        W,
        A
    }
}
