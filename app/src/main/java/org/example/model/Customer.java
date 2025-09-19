package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Vector;

public class Customer {
    @Getter
    private final String id;

    @Getter
    private final String name;

    @Getter
    private final Vector<Rental> rentals;

    @Setter
    @Getter
    private int frequentRenterPoints;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
        this.rentals = new Vector<>();
    }

    public Customer(String id, String name, Vector<Rental> rentals, int frequentRenterPoints ) {
        this.frequentRenterPoints = frequentRenterPoints;
        this.rentals = rentals;
        this.name = name;
        this.id = id;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getRentalRecord() {
        StringBuilder result = new StringBuilder(String.format("Rental Record for %s%n", name));

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
