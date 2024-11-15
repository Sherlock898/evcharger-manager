package com.noder.cargadorws.ocpp.messages.types;

import java.time.Instant;

public record ChargingSchedule(Integer duration, Instant startSchedule, ChargingRateUnit chargingRateUnit, ChargingSchedulePeriod[] chargingSchedulePeriod, Double minChargingRate) {
    public enum ChargingRateUnit {
        W,
        A
    }
}
