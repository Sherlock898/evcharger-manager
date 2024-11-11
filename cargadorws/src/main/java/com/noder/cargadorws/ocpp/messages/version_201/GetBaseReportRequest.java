package com.noder.cargadorws.ocpp.messages.version_201;

public record GetBaseReportRequest(Integer requestId, ReportBase reportBase) {
    public enum ReportBase {
        ConfigurationInventory,
        FullInventory,
        SummaryInventory
    }
}
