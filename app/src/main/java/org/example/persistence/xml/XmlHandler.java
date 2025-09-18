package org.example.persistence.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.example.model.Customer;
import org.example.model.Movie;
import org.example.model.Rental;
import org.example.persistence.xml.model.XCustomer;
import org.example.persistence.xml.model.XMovie;
import org.example.persistence.xml.model.XRental;

import java.io.File;
import java.io.IOException;

public class XmlHandler {
    public static String XML_PATH = "./xml-storage/";

    public void marshal(Movie movie) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(XMovie.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        XMovie xMovie = XMovie.mapToXMovie(movie);
        marshaller.marshal(xMovie, createFile(XMovie.HOME, xMovie.getId()));
    }

    public void marshal(Customer customer) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(XCustomer.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        XCustomer xCustomer = XCustomer.mapToXCustomer(customer);
        marshaller.marshal(xCustomer, createFile(XCustomer.HOME, xCustomer.getId()));
    }

    public void marshal(Rental rental) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(XRental.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        XRental xRental = XRental.mapToXRental(rental);
        marshaller.marshal(xRental, createFile(XRental.HOME, xRental.getId()));
    }

    private File createFile(String path, String fileName) {
        return new File(String.format("%s/%s/%s.xml", XML_PATH, path, fileName));
    }
}