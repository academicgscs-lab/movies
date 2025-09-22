package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Vector;

public class Customer {
    @Getter
    private final String _id;
    private final String _name;
    @Getter
    private final Vector<Rental> rentals;

    @Setter
    @Getter
    private int frequentRenterPoints;

    public Customer(String id, String name) {
        _id = id;
        _name = name;
        this.rentals = new Vector<>();
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getRentalRecord() {
        StringBuilder result = new StringBuilder(String.format("Rental Record for %s%n", _name));

        // add rented movies
        double debt = 0;
        for (Rental rental : rentals) {
            result.append(rental.toString());
            debt += rental.getDebt();
        }

        //add footer lines
        result.append("Amount owed is ").append(debt).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
        return result.toString();
    }

}
