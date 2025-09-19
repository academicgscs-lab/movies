package org.example.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

// TODO: validate data such as ID
public class Rental {
    @Getter
    private String id;

    @Getter
    private final Movie movie;

    @Getter
    private final Customer customer;

    @Setter
    @Getter
    private double debt;

    @Setter
    @Getter
    private int daysRented;

    public Rental (Movie movie, Customer customer){
        this.movie = movie;
        this.customer = customer;
    }

    public Rental (String id, Movie movie, Customer customer, int daysRented){
        this.id = id;
        this.movie = movie;
        this.customer = customer;
        this.daysRented = daysRented;
    }

    public Rental (String id, Movie movie, Customer customer, int daysRented, double debt){
        this.id = id;
        this.movie = movie;
        this.customer = customer;
        this.daysRented = daysRented;
        this.debt = debt;
    }

    @Override
    @NonNull
    public String toString() {
        return String.format("\t%s\t%s%n", movie.title(), debt);
    }
}