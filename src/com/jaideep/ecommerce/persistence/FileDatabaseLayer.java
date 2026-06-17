package com.jaideep.ecommerce.persistence;

import com.jaideep.ecommerce.exceptions.EcommerceException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * File-based implementation of {@link DatabaseLayer} that serialises
     * the entire {@link AppData} object graph to a single binary file.
     *
     * <p>The data file path can be overridden at start-up by setting the
     * {@code ECOM_DATA_PATH} environment variable; it defaults to
     * {@code data/ecommerce.ser}.</p>
     */
public class FileDatabaseLayer implements DatabaseLayer {

    private static final String DEFAULT_DATA_PATH = "data/ecommerce.ser";
        static final String DATA_PATH =
                System.getenv("ECOM_DATA_PATH") != null
                        ? System.getenv("ECOM_DATA_PATH")
                        : DEFAULT_DATA_PATH;

    private static final FileDatabaseLayer INSTANCE = new FileDatabaseLayer();

    private FileDatabaseLayer() {}

    public static FileDatabaseLayer getInstance() {
                return INSTANCE;
    }

    @Override
        public AppData load() {
                    File file = new File(DATA_PATH);
                    if (!file.exists()) {
                                    return new AppData();
                    }
                    try (ObjectInputStream objectInputStream =
                                              new ObjectInputStream(new FileInputStream(file))) {
                                    return (AppData) objectInputStream.readObject();
                    } catch (IOException | ClassNotFoundException e) {
                                    throw new EcommerceException("Unable to load persisted data: " + e.getMessage(), e);
                    }
        }

    @Override
        public void save(AppData data) {
                    File file = new File(DATA_PATH);
                    File parent = file.getParentFile();
                    if (parent != null && !parent.exists() && !parent.mkdirs()) {
                                    throw new EcommerceException("Unable to create data directory: " + parent.getAbsolutePath());
                    }
                    try (ObjectOutputStream objectOutputStream =
                                              new ObjectOutputStream(new FileOutputStream(file))) {
                                    objectOutputStream.writeObject(data);
                    } catch (IOException e) {
                                    throw new EcommerceException("Unable to save data: " + e.getMessage(), e);
                    }
        }
}
