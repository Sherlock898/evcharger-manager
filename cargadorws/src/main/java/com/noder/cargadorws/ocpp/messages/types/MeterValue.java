package com.noder.cargadorws.ocpp.messages.types;
import java.time.Instant;

public record MeterValue(Instant timestamp, SampledValue[] sampledValue) {
}