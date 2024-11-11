package com.noder.cargadorws.ocpp.messages.version_201.types;
import java.util.Date;

public record IdTokenInfo(Status status, Date cacheExpiryDateTime, Integer chargingPriority, String  language1, Integer[] evseId, String  language2, GroupIdToken groupIdToken, PersonalMessage personalMessage) {
    public enum Status {
        Accepted,
        Blocked,
        ConcurrentTx,
        Expired,
        Invalid,
        NoCredit,
        NotAllowedTypeEVSE,
        NotAtThisLocation,
        NotAtThisTime,
        Unknown
    }
}
