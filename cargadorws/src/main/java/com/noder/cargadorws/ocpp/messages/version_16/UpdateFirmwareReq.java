package com.noder.cargadorws.ocpp.messages.version_16;
import java.util.Date;

public record UpdateFirmwareReq(String location, Integer retries, Date retrieveDate, Integer retryInterval) {}