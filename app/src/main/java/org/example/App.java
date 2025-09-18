package org.example;

import jakarta.xml.bind.JAXBException;
import org.example.managers.StoreManager;
import org.example.model.Customer;
import org.example.model.Movie;

import java.io.IOException;

public class App{
    public static void main(String[] args) throws JAXBException, IOException {
        StoreManager storeManager = new StoreManager();
        Customer customer = storeManager.createCustomer("Test");

        Movie soul = storeManager.createMovie("Soul", 2);
        Movie terminator = storeManager.createMovie("Terminator", 0);
        Movie justiceLeague = storeManager.createMovie("Zack Snyder's Justice League", 1);

        storeManager.borrowMovie(justiceLeague.id(), customer, 3);
        storeManager.borrowMovie(soul.id(), customer, 5);
        storeManager.borrowMovie(terminator.id(), customer, 1);

        System.out.println(customer.getRentalRecord());
//        new XmlHandler().marshal(customer);
    }
}
