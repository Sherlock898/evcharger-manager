package com.noder.cargadorws.Types;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

import com.noder.cargadorws.ocpp.messages.StatusNotificationReq.ChargePointErrorCode;
import com.noder.cargadorws.ocpp.messages.StatusNotificationReq.ChargePointStatus;
import com.noder.cargadorws.ocpp.messages.types.MeterValue;


public class Connector {
    private int id;
    private ChargePointStatus status;
    private ChargePointErrorCode errorCode;
    private Deque<MeterValue> meterValues;

    public Connector(int id){
        this.id = id;
        this.meterValues = new ArrayDeque<>();
    }

    public void updateStatus(ChargePointStatus status, ChargePointErrorCode errorCode){
        this.status = status;
        this.errorCode = status == ChargePointStatus.Faulted ? errorCode : null;
    }

    public void addMeterValues(MeterValue[] meterValue) {
        for(int i = 0; i < meterValue.length; i++) {
            meterValues.add(meterValue[i]);
        }
    }

    /**
     * Método para eliminar los MeterValues que tengan más de una semana en la deque.
     * @param thresholdTime es el tiempo en milisegundos que representa el límite (una semana).
     */
    public void cleanMeterValues(long thresholdTime) {
        synchronized (meterValues) {
            Iterator<MeterValue> iterator = meterValues.iterator();
            while(iterator.hasNext()) {
                MeterValue meterValue = iterator.next();
                if(meterValue.timestamp().toEpochMilli() <= thresholdTime) {
                    iterator.remove();
                }
                else {
                    break;
                }
            }
        }
    }
}