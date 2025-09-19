package org.example.persistence.xml;

import org.example.managers.StoreManager;
import org.example.model.Customer;
import org.example.model.Movie;
import org.example.model.Rental;
import org.example.persistence.StorageHandler;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoaderTest {

    @Test
    void coldLoadTest(){
        StoreManager controlStoreManager = getStoreManager();
        StorageHandler handler = new XmlStorageHandler();
        handler.save(controlStoreManager);
        StoreManager storeManager = new StoreManager();
        handler.load(storeManager);

        Customer[] customers = storeManager.getCustomerManager().getItems().values().toArray(new Customer[0]);
        Customer[] controlCustomers = controlStoreManager.getCustomerManager().getItems().values().toArray(new Customer[0]);

        // basic comparison
        assertEquals(controlCustomers.length, customers.length);

        for (int i = 0; i < customers.length; i++) {
            Customer customer = customers[i];
            Customer controlCustomer = controlCustomers[i];

            // body comparison
            assertEquals(customer.getId(), controlCustomer.getId());
            assertEquals(customer.getFrequentRenterPoints(), controlCustomer.getFrequentRenterPoints());

            // rental comparison
            Vector<Rental> loadedRentals = customer.getRentals();
            Vector<Rental> controlRentals = controlCustomer.getRentals();
            assertEquals(loadedRentals.size(), controlRentals.size());

            controlRentals.forEach(item -> assertTrue(loadedRentals.contains(item)));
        }
    }

    private static StoreManager getStoreManager() {
        StoreManager storeManager = new StoreManager();
        Customer john = storeManager.createCustomer("Test");

        Movie soul = storeManager.createMovie("Soul", 2);
        Movie terminator = storeManager.createMovie("Terminator", 0);
        Movie justiceLeague = storeManager.createMovie("Zack Snyder's Justice League", 1);

        storeManager.borrowMovie(justiceLeague.id(), john.getId(), 3);
        storeManager.borrowMovie(soul.id(), john.getId(), 5);
        storeManager.borrowMovie(terminator.id(), john.getId(), 1);
        return storeManager;
    }
}