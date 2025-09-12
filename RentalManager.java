package movies;

import java.util.List;
import java.util.Vector;

public class RentalManager {
    private final Vector<RentedCustomerMovie> rentedCustomerMovies;
    private double _totalAmount;
    private int _frequentRenterPoints;

    public RentalManager() {
        rentedCustomerMovies = new Vector<>();
        _totalAmount = 0;
    }

    public void registerRental(Rental rental) {
        double partialOccurences = calculateBonus(rental);

        // add frequent renter points for Children and New Release
        // keep in mind Regular movies don't have frequent renter points
        if (rental._movie().getPriceCode() == Movie.NEW_RELEASE || rental._movie().getPriceCode() == Movie.CHILDREN) {
            _frequentRenterPoints++;
        }

        // add bonus for a two day new release rental
        if (rental._movie().getPriceCode() == Movie.NEW_RELEASE && rental._daysRented() > 1) {
            _frequentRenterPoints++;
        }

        //show figures for this rental
        rentedCustomerMovies.add(new RentedCustomerMovie(rental._movie().getTitle(), partialOccurences));
        _totalAmount += partialOccurences;
    }

    private double calculateBonus(Rental rental) {
        double bonus = 0;
        switch (rental._movie().getPriceCode()) {
            case Movie.REGULAR:
                bonus += 2;
                if (rental._daysRented() > 2)
                    bonus += (rental._daysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                bonus += rental._daysRented() * 3;
                break;
            case Movie.CHILDREN:
                bonus += 1.5;
                if (rental._daysRented() > 3)
                    bonus += (rental._daysRented() - 3) * 1.5;
                break;
        }
        return bonus;
    }

    public List<RentedCustomerMovie> getRentedMovies() {
        return rentedCustomerMovies;
    }

    public double getTotalAmount() {
        return _totalAmount;
    }

    public int getFrequentRenterPoints() {
        return _frequentRenterPoints;
    }
}
