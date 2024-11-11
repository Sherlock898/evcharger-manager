package com.noder.cargadorws.ocpp.messages.version_201.types;

public record ChargingProfileCriteria(Integer evseId, ChargingProfilePurpose chargingProfilePurpose) {
    public enum ChargingProfilePurpose {
        ChargingStationExternalConstraints,
        ChargingStationMaxProfile,
        TxDefaultProfile,
        TxProfile
    }
}
