package org.example.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.example.utils.Printable;

import java.util.Objects;

// TODO: validate data such as ID
public class Rental implements Printable {
    @Getter
    private final String id;

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

    public Rental(String id, Movie movie, Customer customer, int daysRented) {
        this.id = id;
        this.movie = movie;
        this.customer = customer;
        this.daysRented = daysRented;
    }

    public Rental(String id, Movie movie, Customer customer, int daysRented, double debt) {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Rental customer = (Rental) obj;
        return Objects.equals(id, customer.id);
    }

    @Override
    public String getTitle() {
        return id;
    }

    @Override
    public String getBody() {
        return toString();
    }
}