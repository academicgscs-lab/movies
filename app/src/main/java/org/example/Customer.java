package org.example;

public class Customer {
    private final String _name;
    private final RentalManager rentalManager;

    public Customer(String name) {
        _name = name;
        this.rentalManager = new RentalManager();
    }

    public void addRental(Rental rental) {
        rentalManager.registerRental(rental);
    }

    public String getRentalRecord() {
        StringBuilder result = new StringBuilder(String.format("Rental Record for %s%n", _name));

        // add rented movies
        rentalManager.getRentedMovies().forEach(movie -> result.append(movie.toString()));

        //add footer lines
        result.append("Amount owed is ").append(rentalManager.getTotalAmount()).append("\n");
        result.append("You earned ").append(rentalManager.getFrequentRenterPoints()).append(" frequent renter points");
        return result.toString();
    }
}
