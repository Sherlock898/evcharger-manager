package com.noder.cargadorws.ocpp.messages.version_201;
import com.noder.cargadorws.ocpp.messages.version_201.types.CertificateHashData;

public record DeleteCertificateRequest(CertificateHashData certificateHashData) {}
