package com.noder.cargadorws.ocpp.messages.version_201.types;

public record ChargingProfile(ChargingProfilePurpose chargingProfilePurpose, Integer stackLevel, Integer[] chargingProfileId, ChargingLimitSource[] chargingLimitSource) {
    public enum ChargingProfilePurpose {
        ChargingStationExternalConstraints,
        ChargingStationMaxProfile,
        TxDefaultProfile,
        TxProfile
    }
    
    public enum ChargingLimitSource {
        EMS,
        Other,
        SO,
        CSO
    }
}
