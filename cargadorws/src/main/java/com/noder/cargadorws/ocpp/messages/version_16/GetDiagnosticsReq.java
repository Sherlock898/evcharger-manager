package com.noder.cargadorws.ocpp.messages;
import java.util.Date;

public record GetDiagnosticsReq(String location, Integer retries, Integer retryInterval, Date startTime, Date stopTime) {}
