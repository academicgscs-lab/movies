package org.example.persistence.xml;

import jakarta.xml.bind.JAXBException;
import org.example.managers.StoreManager;
import org.example.managers.model.CustomerManager;
import org.example.managers.model.MovieManager;
import org.example.model.Customer;
import org.example.model.Movie;
import org.example.model.Rental;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class XmlHandlerTest {
    @Test
    void getMovie() {
        StoreManager storeManager = new StoreManager();
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
            xmlHandler.loadMovies();
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getRental() {
        StoreManager storeManager = new StoreManager();
        CustomerManager customerManager = storeManager.getCustomerManager();
        MovieManager movieManager = storeManager.getMovieManager();

        Customer customer = storeManager.createCustomer("Test");

        Movie soul = storeManager.createMovie("Soul", 2);
        Movie terminator = storeManager.createMovie("Terminator", 0);
        Movie justiceLeague = storeManager.createMovie("Zack Snyder's Justice League", 1);

        storeManager.borrowMovie(justiceLeague.id(), customer.getId(), 3);
        storeManager.borrowMovie(soul.id(), customer.getId(), 5);
        storeManager.borrowMovie(terminator.id(), customer.getId(), 1);


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
            xmlHandler.loadMovies();
            xmlHandler.loadRentals(customerManager, movieManager);
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getCustomer() {
        StoreManager storeManager = new StoreManager();
        CustomerManager customerManager = storeManager.getCustomerManager();
        MovieManager movieManager = storeManager.getMovieManager();
        Customer customer = storeManager.createCustomer("Test");

        Movie soul = storeManager.createMovie("Soul", 2);
        Movie terminator = storeManager.createMovie("Terminator", 0);
        Movie justiceLeague = storeManager.createMovie("Zack Snyder's Justice League", 1);

        storeManager.borrowMovie(justiceLeague.id(), customer.getId(), 3);
        storeManager.borrowMovie(soul.id(), customer.getId(), 5);
        storeManager.borrowMovie(terminator.id(), customer.getId(), 1);

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
            xmlHandler.loadMovies();
            xmlHandler.loadRentals(customerManager, movieManager);
            xmlHandler.loadCustomers();
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    // assumes there is already xml data
    void coldLoadTest(){
        StoreManager storeManager = new StoreManager();
        Customer john = storeManager.createCustomer("Test");

        Movie soul = storeManager.createMovie("Soul", 2);
        Movie terminator = storeManager.createMovie("Terminator", 0);
        Movie justiceLeague = storeManager.createMovie("Zack Snyder's Justice League", 1);

        storeManager.borrowMovie(justiceLeague.id(), john.getId(), 3);
        storeManager.borrowMovie(soul.id(), john.getId(), 5);
        storeManager.borrowMovie(terminator.id(), john.getId(), 1);

        // persistence
        XmlHandler xmlHandler = new XmlHandler();
        try {
            // marshal movies
            xmlHandler.marshal(soul);
            xmlHandler.marshal(justiceLeague);
            xmlHandler.marshal(terminator);

            // marshal customer
            xmlHandler.marshal(john);

            // marshal rentals
            john.getRentals().forEach(rental -> {
                try {
                    xmlHandler.marshal(rental);
                } catch (JAXBException | IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }

        // Cold load
        CustomerManager customerManager = new CustomerManager();
        MovieManager movieManager = new MovieManager();

        try {
            // load movies
            xmlHandler.loadMovies().forEach(movie -> movieManager.addItem(movie.id(), movie));
            xmlHandler.loadCustomers().forEach(item -> customerManager.addItem(item.getId(), item));
            Vector<Rental> loadRentals = xmlHandler.loadRentals(customerManager, movieManager);
            for (Rental rental : loadRentals) {
                rental.getCustomer().getRentals().add(rental);
            }

            Customer[] customers = customerManager.getItems().values().toArray(new Customer[0]);
            Customer[] controlCustomers = storeManager.getCustomerManager().getItems().values().toArray(new Customer[0]);

            // basic comparison
            assertEquals(controlCustomers.length, customers.length);

            for (int i = 0; i < customers.length; i++) {
                Customer customer = customers[i];
                Customer controlCustomer = controlCustomers[i];

                // body comparison
                assertEquals(customer.getId(), controlCustomer.getId());
                assertEquals(customer.getFrequentRenterPoints(), controlCustomer.getFrequentRenterPoints());

                // rental comparison
                Vector<Rental> rentals = customer.getRentals();
                Vector<Rental> controlRentals = controlCustomer.getRentals();
                assertEquals(rentals.size(), controlRentals.size());

                controlRentals.forEach(item -> assertTrue(rentals.contains(item)));
            }

        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}