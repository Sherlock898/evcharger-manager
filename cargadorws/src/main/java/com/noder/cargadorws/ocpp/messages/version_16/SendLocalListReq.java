package com.noder.cargadorws.ocpp.messages;
import com.noder.cargadorws.ocpp.messages.types.AuthorizationData;

public record SendLocalListReq(Integer listVersion, AuthorizationData[] localAuthorizationList, UpdateType updateType) {
    public enum UpdateType {
        Differential,
        Full
    }
}
