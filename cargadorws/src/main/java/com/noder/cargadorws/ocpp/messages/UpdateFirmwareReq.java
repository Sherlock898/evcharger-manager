package com.noder.cargadorws.ocpp.messages;

import java.time.Instant;

public record UpdateFirmwareReq(String location, Integer retries, Instant retrieveDate, Integer retryInterval) {
}