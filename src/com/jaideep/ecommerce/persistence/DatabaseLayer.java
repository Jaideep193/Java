package com.jaideep.ecommerce.persistence;

public interface DatabaseLayer {
    AppData load();
    void save(AppData data);
}
