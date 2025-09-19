package org.example.persistence.xml;

import org.example.managers.StoreManager;
import org.example.persistence.StorageHandler;

public class XmlStorageHandler implements StorageHandler {
    private final Marshaller marshaller;
    private final Loader loader;

    public XmlStorageHandler() {
        this.marshaller = new Marshaller();
        this.loader = new Loader();
    }

    @Override
    public void load(StoreManager storeManager) {
        loader.populate(storeManager.getCustomerManager(), storeManager.getMovieManager());
    }

    @Override
    public void save(StoreManager storeManager) {
        marshaller.marshal(storeManager.getCustomerManager(), storeManager.getMovieManager());
    }
}
