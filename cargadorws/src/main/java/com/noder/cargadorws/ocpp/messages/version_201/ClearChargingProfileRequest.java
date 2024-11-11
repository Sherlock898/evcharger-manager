package com.noder.cargadorws.ocpp.messages.version_201;
import com.noder.cargadorws.ocpp.messages.version_201.types.ChargingProfileCriteria;

public record ClearChargingProfileRequest(Integer chargingProfileId, ChargingProfileCriteria chargingProfileCriteria) {}
