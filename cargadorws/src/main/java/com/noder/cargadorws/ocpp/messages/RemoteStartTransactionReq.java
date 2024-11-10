package com.noder.cargadorws.ocpp.messages;

import com.noder.cargadorws.ocpp.messages.types.ChargingProfile;

public record RemoteStartTransactionReq(Integer connectorId, String idTag, ChargingProfile chargingProfile) {
    
}
