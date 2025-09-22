package org.example;

import org.example.managers.StoreManager;
import org.example.mock.Mocker;
import org.example.persistence.StorageHandler;
import org.example.persistence.xml.XmlStorageHandler;

public class App {
    public static String LOCAL_PATH = System.getProperty("user.dir");

    public static void main(String[] args) {
        StoreManager storeManager = new StoreManager();
        StorageHandler storageHandler = new XmlStorageHandler(storeManager);
        storageHandler.load();

        Mocker.mock(storeManager);
        new Controller(storeManager.getCustomerManager(), storeManager.getMovieManager()).run();
        storageHandler.save();
    }
}
