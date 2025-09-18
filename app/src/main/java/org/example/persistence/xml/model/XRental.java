package org.example.persistence.xml.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
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
    private XMovie movie;

    @XmlElement
    @Getter
    @Setter
    private XCustomer customer;

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

    public XRental(String id, XMovie movie, XCustomer customer, double debt, int daysRented) {
        this.id = id;
        this.movie = movie;
        this.customer = customer;
        this.debt = debt;
        this.daysRented = daysRented;
    }

    public static XRental mapToXRental(Rental rental) {
        return new XRental(rental.getId(),
                XMovie.mapToXMovie(rental.getMovie()),
                XCustomer.mapToXCustomer(rental.getCustomer()),
                rental.getDebt(),
                rental.getDaysRented());
    }
}
