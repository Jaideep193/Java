package com.jaideep.ecommerce.services;

import com.jaideep.ecommerce.persistence.AppData;
import com.jaideep.ecommerce.persistence.DatabaseLayer;

public class AppContext {
    private final DatabaseLayer databaseLayer;
    private final AppData appData;

    public AppContext(DatabaseLayer databaseLayer) {
        this.databaseLayer = databaseLayer;
        this.appData = databaseLayer.load();
    }

    public AppData getAppData() {
        return appData;
    }

    public void save() {
        databaseLayer.save(appData);
    }
}
