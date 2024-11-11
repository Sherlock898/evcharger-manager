package com.noder.cargadorws.ocpp.messages.version_201;

public record FirmwareStatusNotificationRequest(Status status, Integer requestId) {
    public enum Status {
        Downloaded,
        DownloadFailed,
        Downloading,
        DownloadScheduled,
        DownloadPaused,
        Idle,
        InstallationFailed,
        Installing,
        Installed,
        InstallRebooting,
        InstallScheduled,
        InstallVerificationFailed,
        InvalidSignature,
        SignatureVerified
    }
}
