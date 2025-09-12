package movies;

public record RentedCustomerMovie(String title, double partialOccurrences) {

    @Override
    public String toString() {
        return String.format("\t%s\t%s%n", title, partialOccurrences);
    }
}
