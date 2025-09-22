package org.example.mock;

import org.example.managers.StoreManager;
import org.example.model.Customer;
import org.example.model.Movie;

public class Mocker {
    public static void mock(StoreManager storeManager){
        Customer customer = storeManager.createCustomer("Ferdinanz");

        Movie soul = storeManager.createMovie("Soul", 2);
        Movie terminator = storeManager.createMovie("Terminator", 0);
        Movie justiceLeague = storeManager.createMovie("Zack Snyder's Justice League", 1);

        storeManager.borrowMovie(justiceLeague.id(), customer.getId(), 3);
        storeManager.borrowMovie(soul.id(), customer.getId(), 5);
        storeManager.borrowMovie(terminator.id(), customer.getId(), 1);
    }
}
