package com.jaideep.ecommerce.persistence;

import com.jaideep.ecommerce.exceptions.EcommerceException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileDatabaseLayer implements DatabaseLayer {
    private static final String DATA_PATH = "data/ecommerce.ser";
    private static final FileDatabaseLayer INSTANCE = new FileDatabaseLayer();

    private FileDatabaseLayer() {
    }

    public static FileDatabaseLayer getInstance() {
        return INSTANCE;
    }

    @Override
    public AppData load() {
        File file = new File(DATA_PATH);
        if (!file.exists()) {
            return new AppData();
        }
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            Object data = objectInputStream.readObject();
            if (data instanceof AppData) {
                return (AppData) data;
            }
            throw new EcommerceException("Invalid data format in persistence file.");
        } catch (IOException | ClassNotFoundException e) {
            throw new EcommerceException("Unable to load persisted data: " + e.getMessage());
        }
    }

    @Override
    public void save(AppData data) {
        File file = new File(DATA_PATH);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists() && !parent.mkdirs()) {
            throw new EcommerceException("Unable to create data directory.");
        }
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(data);
        } catch (IOException e) {
            throw new EcommerceException("Unable to save data: " + e.getMessage());
        }
    }
}
