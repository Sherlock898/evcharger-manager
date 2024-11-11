package com.noder.cargadorws.ocpp.messages;

public record ClearChargingProfileReq(Integer id, Integer connectorId, ChargingProfilePurpose chargingProfilePurpose, Integer stackLevel) {
    public enum ChargingProfilePurpose {
        ChargePointMaxProfile,
        TxDefaultProfile
    }
}
