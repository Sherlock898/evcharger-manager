package com.noder.cargadorws.ocpp.messages;

public record GetCompositeScheduleReq(Integer  connectorId, Integer duration, ChargingRateUnitType chargingRateUnit) {
    public enum ChargingRateUnitType {
        W,
        A
    }
}
