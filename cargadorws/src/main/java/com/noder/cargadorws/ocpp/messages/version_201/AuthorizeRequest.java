package com.noder.cargadorws.ocpp.messages.version_201;
import com.noder.cargadorws.ocpp.messages.version_201.types.IdToken;
import com.noder.cargadorws.ocpp.messages.version_201.types.Iso15118CertificateHashData;

public record AuthorizeRequest(String certificate, IdToken idToken, Iso15118CertificateHashData[] iso15118CertificateHashData) {}
