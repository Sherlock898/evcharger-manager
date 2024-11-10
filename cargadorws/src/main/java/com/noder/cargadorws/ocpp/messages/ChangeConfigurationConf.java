package com.noder.cargadorws.ocpp.messages;

public record ChangeConfigurationConf(Status  status) {
    public enum Status {
        Accepted,
        Rejected,
        RebootRequired,
        NotSupported
    }
}
