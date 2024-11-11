package com.noder.cargadorws.ocpp.messages.version_201.types;

public record PersonalMessage(Format format, String language, String content) {
    public enum Format {
        ASCII,
        HTML,
        URI,
        UTF8
    }
}
