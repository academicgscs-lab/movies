package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.example.utils.Printable;

import java.util.Vector;

public class Customer implements Printable {
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
        result.append("\r\n");
        return result.toString();
    }

    @Override
    public String getTitle() {
        return id;
    }

    @Override
    public String getBody() {
        return String.format("%s - %s", name, getRentalRecord());
    }
}
