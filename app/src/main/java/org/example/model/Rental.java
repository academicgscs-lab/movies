package org.example.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

// TODO: validate data such as ID
public class Rental {

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

    public Rental (Movie _movie, Customer _customer){
        this.movie = _movie;
        this.customer = _customer;
    }

    public Rental (Movie _movie, Customer _customer, int daysRented){
        this.movie = _movie;
        this.customer = _customer;
        this.daysRented = daysRented;
    }

    @Override
    @NonNull
    public String toString() {
        return String.format("\t%s\t%s%n", movie.title(), debt);
    }
}