package com.noder.cargadorws.ocpp.messages.version_16.types;
import java.util.Date;

public record MeterValue(Date dateTime, SampledValue[] sampledValue) {
}