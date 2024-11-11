package com.noder.cargadorws.ocpp.messages.version_201;
import com.noder.cargadorws.ocpp.messages.version_201.types.ChargingProfile;

public record GetChargingProfilesRequest(Integer requestId, Integer evseId, ChargingProfile chargingProfile) {}
