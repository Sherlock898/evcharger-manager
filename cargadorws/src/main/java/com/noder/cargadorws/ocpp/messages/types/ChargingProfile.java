package com.noder.cargadorws.ocpp.messages.types;
import java.util.Date;

public record ChargingProfile(
    Integer chargingProfileId,
    Integer transactionId,
    Integer stackLevel,
    ChargingProfilePurposeType chargingProfilePurpose,
    ChargingProfileKindType chargingProfileKindType,
    RecurrencyKindType recurrencyKind,
    Date validFrom,
    Date validTo,
    ChargingSchedule chargingSchedule
) {
    public enum ChargingProfilePurposeType {
        ChargePointMaxProfile,
        TxDefaultProfile,
        TxProfile
    }

    public enum ChargingProfileKindType {
        Absolute,
        Recurring,
        Relative
    }

    public enum RecurrencyKindType {
        Daily,
        Weekly
    }
}