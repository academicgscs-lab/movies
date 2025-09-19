package org.example.managers.bonus;

import org.example.model.Movie;
import org.example.model.Rental;

public class NewReleaseBonusChecker implements BonusChecker {

    @Override
    public boolean check(Rental rental) {
        // add bonus for a two day new release rental
        int priceCode = rental.getMovie().priceCode();
        return priceCode == Movie.NEW_RELEASE && rental.getDaysRented() > 1;
    }
}
