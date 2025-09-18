package org.example;

import jakarta.xml.bind.JAXBException;
import org.example.handler.xml.XmlHandler;
import org.example.handler.xml.model.XCustomer;

import java.io.IOException;

public class App{
    public static void main(String[] args) throws JAXBException, IOException {
        Customer customer = new Customer("Test");
        customer.addRental(new Rental(new Movie("Zack Snyder's Justice League", 1), 5));
        customer.addRental(new Rental(new Movie("Terminator", 0), 1));
        customer.addRental(new Rental(new Movie("Soul", 2), 3));
        System.out.println(customer.getRentalRecord());

        new XmlHandler().marshal(customer);
    }
}
