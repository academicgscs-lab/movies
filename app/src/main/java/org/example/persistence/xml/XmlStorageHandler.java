package org.example.persistence.xml;

import org.example.App;
import org.example.managers.StoreManager;
import org.example.persistence.StorageHandler;
import org.example.persistence.xml.loaders.*;

import java.util.Vector;

public class XmlStorageHandler extends StorageHandler {
    public static String XML_PATH = String.format("%s/persistence/xml/", App.LOCAL_PATH);

    private final Vector<Loadable<?>> loadables = new Vector<>();
    private final Vector<Marshallable> marshallables = new Vector<>();

    public XmlStorageHandler(StoreManager storeManager) {
        super(storeManager);
        XMovieLoader movieLoader = new XMovieLoader(storeManager.getMovieManager());
        XCustomerLoader xCustomerLoader = new XCustomerLoader(storeManager.getCustomerManager());
        XRentalLoader xRentalLoader = new XRentalLoader(storeManager.getCustomerManager(), storeManager.getMovieManager());

        loadables.add(movieLoader);
        loadables.add(xCustomerLoader);
        loadables.add(xRentalLoader);

        marshallables.add(movieLoader);
        marshallables.add(xCustomerLoader);
        marshallables.add(xRentalLoader);
    }

    @Override
    public void load() {
        for (Loadable<?> loader : loadables) {
            if (loader.load().isEmpty()) {
                throw  new RuntimeException("Error loading storage object");
            }
        }
    }

    @Override
    public void save() {
        for (Marshallable loader : marshallables) {
            if (!loader.store()) {
                throw  new RuntimeException("Error loading storage object");
            }
        }
    }
}
