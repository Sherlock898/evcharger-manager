package com.noder.cargadorws.ocpp.messages.version_16;

import java.util.Date;

public record StartTransactionReq(String connectorId, String idTag, Integer meterStart, Integer reservationId, Date timestamp) {}
