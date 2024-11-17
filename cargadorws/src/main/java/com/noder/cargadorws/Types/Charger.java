package com.noder.cargadorws.Types;

import java.time.Instant;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;

import com.noder.cargadorws.ocpp.messages.ChangeAvailabilityReq;
import com.noder.cargadorws.ocpp.messages.DiagnosticsStatusNotificationReq.StatusDiagnostics;
import com.noder.cargadorws.ocpp.messages.FirmwareStatusNotificationReq.StatusFirmware;
import com.noder.cargadorws.ocpp.messages.StatusNotificationReq.ChargePointErrorCode;
import com.noder.cargadorws.ocpp.messages.StatusNotificationReq.ChargePointStatus;
import com.noder.cargadorws.ocpp.messages.types.MeterValue;

public class Charger {
    private String id;
    private ChargePointStatus status;
    private ChargePointErrorCode errorCode;
    private StatusDiagnostics statusDiagnostics;
    private StatusFirmware statusFirmware;
    private ArrayList<Connector> connectors;
    private ArrayDeque<MeterValue> meterValues;
    private String model;
    private String vendor;


    // Optional parameters
    public String boxSerialNumber;
    public String chargePointSerialNumber;
    public String firmwareVersion;
    public String iccid;
    public String imsi;
    public String meterSerialNumber;
    public String meterType;

    // Transactions
    ArrayDeque<Transaction> transactions;

    public Charger(String id){
        this.id = id;
    }

    public void loadBootNotificationInfo(String model, String vendor, String boxSerialNumber, String chargePointSerialNumber,
                String firmwareVersion, String iccid, String imsi, String meterSerialNumber, String meterType) {
        this.model = model;
        this.vendor = vendor;
        this.boxSerialNumber = boxSerialNumber;
        this.chargePointSerialNumber = chargePointSerialNumber;
        this.firmwareVersion = firmwareVersion;
        this.iccid = iccid;
        this.imsi = imsi;
        this.meterSerialNumber = meterSerialNumber;
        this.meterType = meterType;
        this.connectors = new ArrayList<>();
        this.transactions = new ArrayDeque<>();
    }
    
    /**
     * Adds a connector to the charger's connector array.
     *
     * @param connector The connector to load.
     */
    public void addConnector(Connector connector) {
        connectors.add(connector);
    }

    public void addMeterValues(Integer connectorId, String chargerId, MeterValue[] meterValue) {
        if(connectorId == 0) {
            for(int i = 0; i < meterValue.length; i++) {
                meterValues.add(meterValue[i]);
            }
        }
        else {
            connectors.get(connectorId).addMeterValues(meterValue);
        }
    }

    public void addMeterValuesConnector(int idConnector, MeterValue[] meterValue) {
        connectors.get(idConnector).addMeterValues(meterValue);
    }

    public void cleanMeterValues(long oneWeekAgo) {
        for (Connector connector : connectors) {
            if (connector != null) {
                connector.cleanMeterValues(oneWeekAgo);
            }
        }
    }

    public String getId() {
        return id;
    }

    public void setDiagnosticsStatus(StatusDiagnostics statusDiagnostics) {
        this.statusDiagnostics = statusDiagnostics;
    }

    public StatusDiagnostics getDiagnosticsStatus() {
        return statusDiagnostics;
    }

    public void setFirmwareStatus(StatusFirmware statusFirmware) {
        this.statusFirmware = statusFirmware;
    }

    public StatusFirmware getFirmwareStatus() {
        return statusFirmware;
    }

    public Integer startTransaction(int connectorId, Integer meterStart, Instant startDate){
        Transaction transaction = new Transaction(connectorId, meterStart, startDate);
        this.transactions.add(transaction);
        return transaction.getTransactionId();
    }

    public void updateStatus(int id, ChargePointStatus status, ChargePointErrorCode errorCode) {
        if (id == 0) { // Update status of charger
            this.status = status;
            this.errorCode = status == ChargePointStatus.Faulted ? errorCode : null;
            return;
        }
        if (id - 1 >= connectors.size()) { // If id not registered register all connectors till that id, (id are
                                          // incremental from 1)
            while (id - 1 >= connectors.size()) {
                connectors.add(new Connector(connectors.size()));
            }
        }
        connectors.get(id - 1).updateStatus(status, errorCode);
    }

    public void stopTransaction(Integer transactionId, Integer meterStop, Instant timestamp) {
        // Start searching for the back of the queue
        Iterator<Transaction> iterator = transactions.descendingIterator();
        while (iterator.hasNext()) {
            Transaction transaction = iterator.next();
            if (transaction.getTransactionId() == transactionId) {
                transaction.endTransaction(meterStop);
                return;
            }
        }
    }
}