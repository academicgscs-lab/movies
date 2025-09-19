package org.example.persistence.xml.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.example.managers.model.CustomerManager;
import org.example.managers.model.MovieManager;
import org.example.model.Customer;
import org.example.model.Rental;

import java.util.Vector;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class XCustomer {
    public static String HOME = "customers";

    @XmlElement
    @Getter
    @Setter
    public String id;

    @XmlElement
    @Getter
    @Setter
    public String name;

    @XmlElement
    @Getter
    @Setter
    public int frequentRenterPoints;

    @XmlElement(name = "rental")
    @Getter
    @Setter
    private Vector<XRental> rentals;

    public XCustomer() {}

    public XCustomer(String id, String name, Vector<XRental> rentals, int frequentRenterPoints) {
        this.id = id;
        this.name = name;
        this.rentals = rentals;
        this.frequentRenterPoints = frequentRenterPoints;
    }

    public static XCustomer mapToXCustomer(Customer customer) {
        Vector<XRental> xRentals = new Vector<>();
        for (Rental rental : customer.getRentals()) {
            xRentals.add(XRental.mapToXRental(rental));
        }
        return new XCustomer(customer.getId(), customer.getName(), xRentals,
                customer.getFrequentRenterPoints());
    }

    public static Customer mapToCustomer(XCustomer xCustomer, CustomerManager customerManager, MovieManager movieManager) {
        Vector<Rental> rentals = new Vector<>();
        for (XRental rental : xCustomer.getRentals()) {
            rentals.add(XRental.mapToRental(customerManager, movieManager, rental));
        }
        return new Customer(
                xCustomer.getId(),
                xCustomer.getName(),
                rentals,
                xCustomer.getFrequentRenterPoints()
        );
    }
}
