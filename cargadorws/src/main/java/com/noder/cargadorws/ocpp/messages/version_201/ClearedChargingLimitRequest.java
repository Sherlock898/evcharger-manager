package com.noder.cargadorws.ocpp.messages.version_201;

public record ClearedChargingLimitRequest(ChargingLimitSource  chargingLimitSource, Integer evseId) {
    public enum  ChargingLimitSource {
        EMS,
        Other,
        SO,
        CSO
    }
}
