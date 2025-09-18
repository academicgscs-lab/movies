package org.example.persistence.xml.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
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
    private Vector<Rental> rentals;

    public XCustomer() {}

    public XCustomer(String id, String name, Vector<Rental> rentals, int frequentRenterPoints) {
        this.id = id;
        this.name = name;
        this.rentals = rentals;
        this.frequentRenterPoints = frequentRenterPoints;
    }

    public static XCustomer mapToXCustomer(Customer customer) {
        return new XCustomer(customer.getId(), customer.getName(), customer.getRentals(),
                customer.getFrequentRenterPoints());
    }
}
