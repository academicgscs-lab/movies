package org.example.persistence;

import org.example.managers.StoreManager;

public abstract class StorageHandler {

    private final StoreManager manager;

    public StorageHandler(StoreManager manager) {
        this.manager = manager;
    }

    public abstract void load();
    public abstract void save();
}
