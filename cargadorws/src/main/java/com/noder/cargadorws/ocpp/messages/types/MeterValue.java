package com.noder.cargadorws.ocpp.messages.types;
import java.util.Date;

public record MeterValue(Date dateTime, SampledValue[] sampledValue) {
}