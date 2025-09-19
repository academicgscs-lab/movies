package org.example.managers;

import org.example.managers.model.CustomerManager;
import org.example.managers.model.MovieManager;
import org.example.model.Customer;
import org.example.model.Movie;
import org.example.model.Rental;
import org.example.utils.UUUIDGenerator;

public class StoreManager {
    private final RentalManager rentalManager;
    private final CustomerManager customerManager;
    private final MovieManager movieManager;

    public StoreManager(CustomerManager customerManager, MovieManager movieManager) {
        this.customerManager = customerManager;
        this.movieManager = movieManager;
        rentalManager = new RentalManager();
    }

    public Customer createCustomer(String name){
        Customer customer = new Customer(UUUIDGenerator.generateUniqueId(), name);
        customerManager.addItem(customer.getId(), customer);
        return customer;
    }

    public Movie createMovie(String title, int priceCode){
        Movie movie = new Movie(UUUIDGenerator.generateUniqueId(), title, priceCode);
        movieManager.addItem(movie.id(), movie);
        return movie;
    }

    public void borrowMovie(String movieId, String customerId, int daysRented){
        // TODO: check if it exists
        Movie movie = movieManager.getItem(movieId);
        Customer customer = customerManager.getItem(customerId);

        Rental rental = new Rental(
                UUUIDGenerator.generateUniqueId(),
                movie,
                customer,
                daysRented);
        rentalManager.register(rental);
    }
}
