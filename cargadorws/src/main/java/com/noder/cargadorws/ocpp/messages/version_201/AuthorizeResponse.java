package com.noder.cargadorws.ocpp.messages.version_201;
import com.noder.cargadorws.ocpp.messages.version_201.types.IdTokenInfo;

public record AuthorizeResponse(CertificateStatus  certificateStatus, IdTokenInfo idTokenInfo) {
    public enum  CertificateStatus {
        Accepted,
        SignatureError,
        CertificateExpired,
        CertificateRevoked,
        NoCertificateAvailable,
        CertChainError,
        ContractCancelled
    }
}
