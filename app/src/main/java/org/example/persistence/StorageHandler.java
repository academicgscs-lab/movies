package org.example.persistence;

import org.example.managers.StoreManager;

public interface StorageHandler {
    void load(StoreManager storeManager);
    void save(StoreManager storeManager);
}
