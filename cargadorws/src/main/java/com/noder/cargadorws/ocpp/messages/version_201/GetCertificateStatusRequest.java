package com.noder.cargadorws.ocpp.messages.version_201;
import com.noder.cargadorws.ocpp.messages.version_201.types.OcspRequestData;

public record GetCertificateStatusRequest(OcspRequestData ocspRequestData) {}
