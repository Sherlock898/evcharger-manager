package com.noder.cargadorws.Types;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

import com.noder.cargadorws.ocpp.messages.StatusNotificationReq.ChargePointStatus;
import com.noder.cargadorws.ocpp.messages.types.MeterValue;


public class Connector {
    private int id;
    private ChargePointStatus status;
    private Deque<MeterValue> meterValueStart;

    public Connector(int id){
        this.id = id;
        this.meterValueStart = new ArrayDeque<>();
    }

    public void updateStatus(ChargePointStatus status){
        this.status = status;
    }
    
    /**
     * Método para eliminar los MeterValues que tengan más de una semana en la deque.
     * @param thresholdTime es el tiempo en milisegundos que representa el límite (una semana).
     */
    public void cleanMeterValues(long thresholdTime) {
        synchronized (meterValueStart) {
            Iterator<MeterValue> iterator = meterValueStart.iterator();
            while(iterator.hasNext()) {
                MeterValue meterValue = iterator.next();
                if(meterValue.timestamp().getTime() <= thresholdTime) {
                    iterator.remove();
                }
                else {
                    break;
                }
            }
        }
    }
}