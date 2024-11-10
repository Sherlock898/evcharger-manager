package com.noder.cargadorws.ocpp.messages;

import java.util.Date;

public record UpdateFirmwareReq(String location, Integer retries, Date retrieveDate, Integer retryInterval) {
}