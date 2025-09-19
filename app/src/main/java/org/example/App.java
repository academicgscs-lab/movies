package org.example;

import jakarta.xml.bind.JAXBException;
import org.example.managers.model.CustomerManager;
import org.example.managers.StoreManager;
import org.example.managers.model.MovieManager;
import org.example.model.Customer;
import org.example.model.Movie;
import org.example.persistence.xml.XmlHandler;

import java.io.IOException;

public class App{
    public static String LOCAL_PATH = System.getProperty("user.dir");

    public static void main(String[] args) throws IOException {
        CustomerManager customerManager = new CustomerManager();
        MovieManager movieManager = new MovieManager();
        StoreManager storeManager = new StoreManager(customerManager, movieManager);
        Customer customer = storeManager.createCustomer("Test");

        Movie soul = storeManager.createMovie("Soul", 2);
        Movie terminator = storeManager.createMovie("Terminator", 0);
        Movie justiceLeague = storeManager.createMovie("Zack Snyder's Justice League", 1);

        storeManager.borrowMovie(justiceLeague.id(), customer, 3);
        storeManager.borrowMovie(soul.id(), customer, 5);
        storeManager.borrowMovie(terminator.id(), customer, 1);

        System.out.println(customer.getRentalRecord());
        XmlHandler xmlHandler = new XmlHandler();
        try {
            xmlHandler.marshal(soul);
            xmlHandler.marshal(customer);
            customer.getRentals().forEach(rental -> {
                try {
                    xmlHandler.marshal(rental);
                } catch (JAXBException | IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
