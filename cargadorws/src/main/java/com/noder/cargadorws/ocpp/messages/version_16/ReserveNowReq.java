package com.noder.cargadorws.ocpp.messages;
import java.util.Date;

public record ReserveNowReq(Integer connectorId, Date expiryDate, String idTag, String parentIdTag, Integer reservationId) {}
