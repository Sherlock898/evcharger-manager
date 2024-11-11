package com.noder.cargadorws.ocpp.messages.version_201.types;

public record ChargingStation(String serialNumber, String model, String vendorName, String firmwareVersion, Modem modem) {}
