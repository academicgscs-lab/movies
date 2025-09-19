package org.example.persistence.xml.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.example.managers.model.CustomerManager;
import org.example.managers.model.MovieManager;
import org.example.model.Rental;

@XmlRootElement(name = "rental")
@XmlAccessorType(XmlAccessType.FIELD)
public class XRental {
    public static String HOME = "rentals";

    @XmlElement
    @Getter
    @Setter
    public String id;

    @XmlElement
    @Getter
    @Setter
    public String movieId;

    @XmlElement
    @Getter
    @Setter
    public String customerId;

    @XmlElement
    @Setter
    @Getter
    private double debt;

    @XmlElement
    @Setter
    @Getter
    private int daysRented;

    public XRental() {
    }

    public XRental(String id, String movieId, String customerId,  double debt, int daysRented) {
        this.id = id;
        this.movieId = movieId;
        this.customerId = customerId;
        this.debt = debt;
        this.daysRented = daysRented;
    }

    public static XRental mapToXRental(Rental rental) {
        return new XRental(rental.getId(),
                rental.getMovie().id(),
                rental.getCustomer().getId(),
                rental.getDebt(),
                rental.getDaysRented());
    }

    public static Rental mapToRental(CustomerManager customerManager, MovieManager movieManager, XRental xRental) {
        return new Rental(xRental.getId(),
                movieManager.getItem(xRental.getMovieId()),
                customerManager.getItem(xRental.getCustomerId()),
                xRental.getDaysRented(),
                xRental.getDebt()
        );
    }
}
