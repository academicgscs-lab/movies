package org.example.managers;

import jakarta.xml.bind.JAXBException;
import org.example.managers.model.CustomerManager;
import org.example.managers.model.MovieManager;
import org.example.model.Customer;
import org.example.model.Movie;
import org.example.model.Rental;
import org.example.persistence.xml.XmlHandler;
import org.example.utils.UUUIDGenerator;

import java.io.IOException;
import java.util.Vector;

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

    public void borrowMovie(String movieId, Customer customer){
        Rental rental = new Rental(movieManager.getItem(movieId), customer);
        rentalManager.register(rental);
    }

    public void borrowMovie(String movieId, Customer customer, int daysRented){
        Rental rental = new Rental(UUUIDGenerator.generateUniqueId(), movieManager.getItem(movieId), customer, daysRented);
        rentalManager.register(rental);
    }

    public void executeColdLoad() throws JAXBException, IOException {
        XmlHandler xmlHandler = new XmlHandler();
        Vector<Movie> movies = xmlHandler.loadMovies();


    }
}
