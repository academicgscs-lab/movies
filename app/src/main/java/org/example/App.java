package org.example;

import jakarta.xml.bind.JAXBException;
import org.example.managers.model.CustomerManager;
import org.example.managers.StoreManager;
import org.example.managers.model.MovieManager;
import org.example.mock.Mocker;
import org.example.persistence.xml.XmlHandler;

import java.io.IOException;

public class App {
    public static String LOCAL_PATH = System.getProperty("user.dir");

    public static void main(String[] args) throws IOException, JAXBException {
        XmlHandler xmlHandler = new XmlHandler();
        StoreManager storeManager = new StoreManager();
        CustomerManager customerManager = storeManager.getCustomerManager();
        MovieManager movieManager = storeManager.getMovieManager();
        xmlHandler.populate(customerManager, movieManager);

        Mocker.populateStore(storeManager);

        new Controller(customerManager, movieManager).run();

        xmlHandler.persist(customerManager);
        xmlHandler.persist(movieManager);
        customerManager.getItems().values().forEach(c -> System.out.println(c.getRentalRecord()));
    }
}
