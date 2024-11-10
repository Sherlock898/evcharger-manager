package com.noder.cargadorws.ocpp.messages;

public record DiagnosticsStatusNotificationReq(Status status) {
    public enum Status {
        Idle,
        Uploaded,
        UploadFailed,
        Uploading
    }
}
