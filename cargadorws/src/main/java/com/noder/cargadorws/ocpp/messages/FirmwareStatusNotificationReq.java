package com.noder.cargadorws.ocpp.messages;

public record FirmwareStatusNotificationReq(StatusFirmware status) {
    public enum StatusFirmware {
        Downloaded,
        DownloadFailed,
        Downloading,
        Idle,
        InstallationFailed,
        Installing,
        Installed
    }
}
