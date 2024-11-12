package com.noder.cargadorws.Types;

import java.util.Deque;
import java.util.concurrent.ScheduledExecutorService;

import com.noder.cargadorws.ocpp.messages.types.MeterValue;

public class Connector {
    private int id;
    private Status status;
    private Deque<MeterValue> meterValueStart;
    private ScheduledExecutorService scheduler;
    // TODO: Clean meter values after some time

    public enum Status {
        Available,
        Preparing,
        Charging,
        SuspendedEVSE,
        SuspendedEV,
        Finishing,
        Reserved,
        Unavailable,
        Faulted
    }

}