package org.example.managers;

import org.example.model.Customer;
import org.example.model.Movie;
import org.example.model.Rental;
import org.example.utils.UUUIDGenerator;

import java.util.HashMap;

public class StoreManager {
    private final HashMap<String, Movie> movies = new HashMap<>();
    private final RentalManager rentalManager = new RentalManager();


    public Customer createCustomer(String name){
        return new Customer(UUUIDGenerator.generateUniqueId(), name);
    }

    public Movie createMovie(String title, int priceCode){
        Movie movie = new Movie(UUUIDGenerator.generateUniqueId(), title, priceCode);
        movies.put(movie.id(), movie);
        return movie;
    }

    public void borrowMovie(String movieId, Customer customer){
        Rental rental = new Rental(movies.get(movieId), customer);
        rentalManager.register(rental);
    }

    public void borrowMovie(String movieId, Customer customer, int daysRented){
        Rental rental = new Rental(movies.get(movieId), customer, daysRented);
        rentalManager.register(rental);
    }
}
