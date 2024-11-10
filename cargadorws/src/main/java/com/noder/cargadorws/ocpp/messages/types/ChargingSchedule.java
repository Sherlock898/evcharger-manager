package com.noder.cargadorws.ocpp.messages.types;
import java.util.Date;

public record ChargingSchedule(Integer duration, Date startSchedule, ChargingRateUnit chargingRateUnit, ChargingSchedulePeriod[] chargingSchedulePeriod, Double minChargingRate) {
    public enum ChargingRateUnit {
        W,
        A
    }
}
