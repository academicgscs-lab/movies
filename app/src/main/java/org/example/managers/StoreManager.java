package org.example.managers;

import lombok.Getter;
import org.example.managers.bonus.NewReleaseBonusChecker;
import org.example.managers.bonus.KidBonusChecker;
import org.example.managers.bonus.BonusChecker;
import org.example.managers.model.CustomerManager;
import org.example.managers.model.MovieManager;
import org.example.model.Customer;
import org.example.model.Movie;
import org.example.model.Rental;
import org.example.utils.UUUIDGenerator;

import java.util.Vector;

public class StoreManager {
    private final RentalManager rentalManager;

    @Getter
    private final CustomerManager customerManager;

    @Getter
    private final MovieManager movieManager;

    public StoreManager() {
        this.customerManager = new CustomerManager();
        this.movieManager = new  MovieManager();

        Vector<BonusChecker> strategies = new Vector<>();
        strategies.add(new NewReleaseBonusChecker());
        strategies.add(new KidBonusChecker());
        rentalManager = new RentalManager(strategies);
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
