package org.example.persistence.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import org.example.App;
import org.example.managers.StoreManager;
import org.example.managers.model.CustomerManager;
import org.example.managers.model.Manager;
import org.example.managers.model.MovieManager;
import org.example.model.Customer;
import org.example.model.Movie;
import org.example.model.Rental;
import org.example.persistence.xml.model.XCustomer;
import org.example.persistence.xml.model.XMovie;
import org.example.persistence.xml.model.XRental;

import java.io.File;
import java.io.IOException;

class Marshaller {
    public static String XML_PATH = String.format("%s/persistence/xml/", App.LOCAL_PATH);

    public void marshal(Movie movie) {
        try {
            JAXBContext context = JAXBContext.newInstance(XMovie.class);
            jakarta.xml.bind.Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(jakarta.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            XMovie xMovie = XMovie.mapToXMovie(movie);
            marshaller.marshal(xMovie, createFile(XMovie.HOME, xMovie.getId()));

        } catch (JAXBException e) {
            System.out.println("Error marshalling movie " + movie.getTitle());
        }
    }

    public void marshal(Customer customer) {
        try {
            JAXBContext context = JAXBContext.newInstance(XCustomer.class);
            jakarta.xml.bind.Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(jakarta.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            XCustomer xCustomer = XCustomer.mapToXCustomer(customer);
            marshaller.marshal(xCustomer, createFile(XCustomer.HOME, xCustomer.getId()));

        } catch (JAXBException e) {
            System.out.println("Error marshalling Customer " + customer.getTitle());
        }
    }

    public void marshal(Rental rental) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(XRental.class);
        jakarta.xml.bind.Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(jakarta.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        XRental xRental = XRental.mapToXRental(rental);
        marshaller.marshal(xRental, createFile(XRental.HOME, xRental.getId()));
    }

    private File createFile(String path, String fileName) {
        return new File(String.format("%s/%s/%s.xml", XML_PATH, path, fileName));
    }

    public void marshal(CustomerManager customerManager, MovieManager movieManager) {
        movieManager.getItems().values().forEach(this::marshal);
        customerManager.getItems().values().forEach(this::marshal);
    }
}
