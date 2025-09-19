package org.example.managers.bonus;

import org.example.model.Movie;
import org.example.model.Rental;

public class KidBonusChecker implements BonusChecker {

    @Override
    public boolean check(Rental rental) {
        // add frequent renter points for Children and New Release
        // keep in mind Regular movies don't have frequent renter points
        int priceCode = rental.getMovie().priceCode();
        return priceCode == Movie.NEW_RELEASE || priceCode == Movie.CHILDREN;
    }
}
