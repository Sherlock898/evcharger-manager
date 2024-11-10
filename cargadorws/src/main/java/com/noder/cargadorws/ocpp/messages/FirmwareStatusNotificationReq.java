package com.noder.cargadorws.ocpp.messages;

public record FirmwareStatusNotificationReq(Status status) {
    public enum Status {
        Downloaded,
        DownloadFailed,
        Downloading,
        Idle,
        InstallationFailed,
        Installing,
        Installed
    }
}
