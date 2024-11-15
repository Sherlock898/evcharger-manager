package com.noder.cargadorws.ocpp.messages;

import java.time.Instant;

public record ReserveNowReq(Integer connectorId, Instant expiryDate, String idTag, String parentIdTag, Integer reservationId) {
    
}
