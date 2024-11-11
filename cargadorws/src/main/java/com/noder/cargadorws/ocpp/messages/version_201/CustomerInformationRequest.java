package com.noder.cargadorws.ocpp.messages.version_201;
import com.noder.cargadorws.ocpp.messages.version_201.types.CustomerCertificate;
import com.noder.cargadorws.ocpp.messages.version_201.types.IdToken;

public record CustomerInformationRequest(Integer requestId, Boolean report, Boolean clear, String customerIdentifier, IdToken idToken, CustomerCertificate customerCertificate) {}
