package com.noder.cargadorws.Types;

import java.util.ArrayList;

import com.noder.cargadorws.ocpp.messages.StatusNotificationReq.ChargePointStatus;

public class Charger {
    private String id;
    private ChargePointStatus status;
    private ArrayList<Connector> connectors;

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
    }
    
    /**
     * Adds a connector to the charger's connector array.
     *
     * @param connector The connector to load.
     */
    public void addConnector(Connector connector) {
        connectors.add(connector);
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

    public void updateStatus(int id, ChargePointStatus status) {
        if (id == 0) { // Update status of charger
            this.status = status;
            return;
        }
        if (id - 1 > connectors.size()) { // If id not registered register all connectors till that id, (id are
                                          // incremental from 1)
            while (id - 1 > connectors.size()) {
                connectors.add(new Connector(connectors.size()));
            }
        }
        connectors.get(id - 1).updateStatus(status);
    }
}