package com.noder.cargadorws.ocpp.messages;

public record DiagnosticsStatusNotificationReq(StatusDiagnostics status) {
    public enum StatusDiagnostics {
        Idle,
        Uploaded,
        UploadFailed,
        Uploading
    }
}
