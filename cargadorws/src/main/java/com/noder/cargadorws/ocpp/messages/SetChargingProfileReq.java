package com.noder.cargadorws.ocpp.messages;

import com.noder.cargadorws.ocpp.messages.types.ChargingProfile;

public record SetChargingProfileReq(Integer connectorId, ChargingProfile csChargingProfiles) {

}
