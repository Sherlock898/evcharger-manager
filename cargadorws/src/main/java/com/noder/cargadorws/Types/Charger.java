package com.noder.cargadorws.Types;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Charger {
    private static ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private String id;
    private Connector[] connectors;

    private static ArrayList<Charger> chargers;

    /*public Charger(String id, int numConnectors) {
        this.id = id;
        this.connectors = new Connector[numConnectors];
        for (int i = 0; i < numConnectors; i++) {
            this.connectors[i] = new Connector(i + 1);
        }
    }

    // Registra un cargador para que su limpieza sea gestionada por el scheduler global
    public static void scheduleMeterValueCleanup(List<Charger> chargers) {
        scheduler.scheduleAtFixedRate(() -> {
            long oneWeekAgo = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(7);
            // Recorre todos los cargadores y limpia sus valores de medidor
            for (Charger charger : chargers) {
                charger.cleanupOldMeterValues(oneWeekAgo);
            }
        }, 1, 1, TimeUnit.DAYS);
    }
    */
}