package org.example.persistence.xml;

import jakarta.xml.bind.JAXBException;
import org.example.managers.StoreManager;
import org.example.managers.model.CustomerManager;
import org.example.managers.model.MovieManager;
import org.example.model.Customer;
import org.example.model.Movie;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class XmlHandlerTest {
    @Test
    void getMovie() {
        CustomerManager customerManager = new CustomerManager();
        MovieManager movieManager = new MovieManager();

        StoreManager storeManager = new StoreManager(customerManager, movieManager);
        Movie soul = storeManager.createMovie("Soul", 2);
        Movie terminator = storeManager.createMovie("Terminator", 0);
        Movie justiceLeague = storeManager.createMovie("Zack Snyder's Justice League", 1);



        XmlHandler xmlHandler = new XmlHandler();
        try {
            xmlHandler.marshal(soul);
            xmlHandler.marshal(terminator);
            xmlHandler.marshal(justiceLeague);
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }

        try {
            xmlHandler.getMovies();
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getRental() {
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

        // persistence
        XmlHandler xmlHandler = new XmlHandler();
        try {
            // marshal movies
            xmlHandler.marshal(soul);
            xmlHandler.marshal(justiceLeague);
            xmlHandler.marshal(terminator);

            // marshal customer
            xmlHandler.marshal(customer);

            // marshal rentals
            customer.getRentals().forEach(rental -> {
                try {
                    xmlHandler.marshal(rental);
                } catch (JAXBException | IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }

        try {
            xmlHandler.getMovies();
            xmlHandler.getRentals(customerManager, movieManager);
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getCustomer() {
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

        // persistence
        XmlHandler xmlHandler = new XmlHandler();
        try {
            // marshal movies
            xmlHandler.marshal(soul);
            xmlHandler.marshal(justiceLeague);
            xmlHandler.marshal(terminator);

            // marshal customer
            xmlHandler.marshal(customer);

            // marshal rentals
            customer.getRentals().forEach(rental -> {
                try {
                    xmlHandler.marshal(rental);
                } catch (JAXBException | IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }

        try {
            xmlHandler.getMovies();
            xmlHandler.getRentals(customerManager, movieManager);
            xmlHandler.getCostumer(customerManager, movieManager);
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}