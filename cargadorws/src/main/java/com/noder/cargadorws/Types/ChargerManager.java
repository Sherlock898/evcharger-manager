package com.noder.cargadorws.Types;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.noder.cargadorws.Types.Exceptions.ChargerNotFoundException;


public class ChargerManager {
    public static final ChargerManager instance = new ChargerManager();
    public final Map<String, Charger> chargers = new ConcurrentHashMap<>();
    private ScheduledExecutorService scheduler;
    private static final long ONE_WEEK_IN_MILLIS = TimeUnit.DAYS.toMillis(7);

    private ChargerManager() {}

    public static ChargerManager getInstance() {
        return instance;
    }

    public Charger addCharger(String id) {
        return chargers.computeIfAbsent(id, key -> new Charger(id));
    }

    public Charger loadBootNotificationInfo(String id, String model, String vendor, String boxSerialNumber, String chargePointSerialNumber,
                String firmwareVersion, String iccid, String imsi, String meterSerialNumber, String meterType) throws ChargerNotFoundException{
        Charger charger;
        try {
            charger = this.getCharger(id);
        } catch (ChargerNotFoundException e) {
            throw e;
        }
        charger.loadBootNotificationInfo(model, vendor, boxSerialNumber, chargePointSerialNumber, firmwareVersion, iccid, imsi, meterSerialNumber, meterType);
        return charger;
    }

    public Charger getCharger(String id) throws ChargerNotFoundException {
        Charger charger = chargers.get(id);
        if (charger == null) {
            throw new ChargerNotFoundException("Charger with id" + id + "not found.\n");
        }
        return charger;
    }

    public void removeCharger(String id) {
        chargers.remove(id);
    }

    public void startMeterValueCleanupTask() {
        if (scheduler == null) {
            scheduler = java.util.concurrent.Executors.newSingleThreadScheduledExecutor();
            scheduler.scheduleAtFixedRate(() -> cleanAllChargersMeterValues(), 1, 1, TimeUnit.DAYS);
        }
    }

    private void cleanAllChargersMeterValues() {
        long oneWeekAgo = System.currentTimeMillis() - ONE_WEEK_IN_MILLIS;
        for (Charger charger : chargers.values()) {
            charger.cleanMeterValues(oneWeekAgo);
        }
    }

    public Map<String, Charger> getAllChargers() {
        return chargers;
    }
}
