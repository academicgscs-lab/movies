package org.example.managers;

import org.example.model.Movie;
import org.example.model.Rental;

public class RentalManager {

    public void register(Rental rental) {
        double debt = calculateDebt(rental);
        rental.setDebt(debt);
        rental.getCustomer().addRental(rental);
        int frequentRentalPoints = calculateFrequentRentalPoints(rental);
        rental.getCustomer().setFrequentRenterPoints(frequentRentalPoints);
    }

    private double calculateDebt(Rental rental) {
        double debt = 0;
        int daysRented = rental.getDaysRented();
        switch (rental.getMovie().priceCode()) {
            case Movie.REGULAR:
                debt += 2;
                if (rental.getDaysRented() > 2)
                    debt += (daysRented - 2) * 1.5;
                break;

            case Movie.NEW_RELEASE:
                debt += daysRented * 3;
                break;

            case Movie.CHILDREN:
                debt += 1.5;
                if (daysRented > 3)
                    debt += (daysRented - 3) * 1.5;
                break;
        }
        return debt;
    }

    private int calculateFrequentRentalPoints(Rental rental) {
        int frequentRenterPoints = rental.getCustomer().getFrequentRenterPoints();

        // add frequent renter points for Children and New Release
        // keep in mind Regular movies don't have frequent renter points
        int priceCode = rental.getMovie().priceCode();
        if (priceCode == Movie.NEW_RELEASE || priceCode == Movie.CHILDREN) {
            frequentRenterPoints++;
        }

        // add bonus for a two day new release rental
        if (priceCode == Movie.NEW_RELEASE && rental.getDaysRented() > 1) {
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }
}
