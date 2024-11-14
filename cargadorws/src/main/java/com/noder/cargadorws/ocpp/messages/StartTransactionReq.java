package com.noder.cargadorws.ocpp.messages;

import java.util.Date;

public record StartTransactionReq(Integer connectorId, String idTag, Integer meterStart, Integer reservationId, Date timestamp) {
    
}
