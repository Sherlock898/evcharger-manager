package com.noder.cargadorws.ocpp.messages;

import com.noder.cargadorws.ocpp.messages.types.MeterValue;

public record MeterValuesReq(Integer connectorId, Integer transactionId, MeterValue[] meterValue) {}
