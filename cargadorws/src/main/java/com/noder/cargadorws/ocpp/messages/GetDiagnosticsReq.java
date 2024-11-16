package com.noder.cargadorws.ocpp.messages;
import java.time.Instant;

public record GetDiagnosticsReq(String location, Integer retries, Integer retryInterval, Instant startTime, Instant stopTime) {}
