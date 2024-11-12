package com.noder.cargadorws.ocpp.messages.types;

import java.util.Date;

public record IdTagInfo(Date expiryDate, String parentIdTag, AuthorizationStatus status) {
    public enum AuthorizationStatus{
        Accepted,
        Blocked,
        Expired,
        Invalid,
        ConcurrentTx
    };
}
