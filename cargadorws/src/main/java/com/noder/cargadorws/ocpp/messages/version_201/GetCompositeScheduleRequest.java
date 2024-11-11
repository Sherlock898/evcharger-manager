package com.noder.cargadorws.ocpp.messages.version_201;

public record GetCompositeScheduleRequest(Integer duration, ChargingRateUnit chargingRateUnit, Integer evseId) {
    public enum ChargingRateUnit {
        W,
        A
    }
}
