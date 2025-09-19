package org.example.managers;

import org.example.managers.bonus.BonusChecker;
import org.example.model.Movie;
import org.example.model.Rental;

import java.util.Vector;

public class RentalManager {
    private final Vector<BonusChecker> bonusCheckers;

    public RentalManager(Vector<BonusChecker> bonusCheckers) {
        this.bonusCheckers = bonusCheckers;
    }

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
        for (BonusChecker bonusChecker : bonusCheckers) {
            if (bonusChecker.check(rental)) {
                frequentRenterPoints++;
            }
        }
        return frequentRenterPoints;
    }
}
