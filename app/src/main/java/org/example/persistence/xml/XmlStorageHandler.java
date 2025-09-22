package org.example.persistence.xml;

import org.example.App;
import org.example.managers.StoreManager;
import org.example.persistence.StorageHandler;
import org.example.persistence.xml.loaders.Loadable;
import org.example.persistence.xml.loaders.XCustomerLoader;
import org.example.persistence.xml.loaders.XMovieLoader;
import org.example.persistence.xml.loaders.XRentalLoader;
import org.example.persistence.xml.model.XCustomer;

import java.util.Vector;

public class XmlStorageHandler implements StorageHandler {
    public static String XML_PATH = String.format("%s/persistence/xml/", App.LOCAL_PATH);

    private final Marshaller marshaller;

    public XmlStorageHandler() {
        this.marshaller = new Marshaller();
    }

    private void handleLoaders(Vector<Loadable<?>> loaders)
    {
        for (Loadable<?> loader : loaders) {
            if (loader.load().isEmpty()) {
                throw  new RuntimeException("Error loading storage object");
            }
        }
    }

    @Override
    public void load(StoreManager storeManager) {
        Vector<Loadable<?>> loadables = new Vector<>();
        loadables.add(new XMovieLoader(storeManager.getMovieManager()));
        loadables.add(new XCustomerLoader(storeManager.getCustomerManager()));
        loadables.add(new XRentalLoader(storeManager.getCustomerManager(), storeManager.getMovieManager()));
        handleLoaders(loadables);
    }

    @Override
    public void save(StoreManager storeManager) {
        marshaller.marshal(storeManager.getCustomerManager(), storeManager.getMovieManager());
    }
}
