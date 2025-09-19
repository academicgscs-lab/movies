package org.example.persistence.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.example.App;
import org.example.managers.model.CustomerManager;
import org.example.managers.model.MovieManager;
import org.example.model.Customer;
import org.example.model.Movie;
import org.example.model.Rental;
import org.example.persistence.xml.model.XCustomer;
import org.example.persistence.xml.model.XMovie;
import org.example.persistence.xml.model.XRental;
import org.example.utils.FileManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Vector;

// TODO: refactor to use a creational design pattern
public class XmlHandler {
    public static String XML_PATH = String.format("%s/persistence/xml/", App.LOCAL_PATH);

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

    public Vector<Movie> getMovies() throws JAXBException, IOException {
        Vector<Movie> movies = new Vector<>();
        JAXBContext context = JAXBContext.newInstance(XMovie.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Vector<Path> files = FileManager.listFiles(String.format("%s/%s", XML_PATH, XMovie.HOME));
        for (Path file : files) {
            XMovie movie = (XMovie) unmarshaller.unmarshal(file.toFile());
            movies.add(XMovie.mapToMovie(movie));
        }
        return movies;
    }

    public Vector<Customer> getCostumer(CustomerManager customerManager, MovieManager movieManager) throws JAXBException, IOException {
        Vector<Customer> items = new Vector<>();
        JAXBContext context = JAXBContext.newInstance(XCustomer.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Vector<Path> files = FileManager.listFiles(String.format("%s/%s", XML_PATH, XCustomer.HOME));
        for (Path file : files) {
            XCustomer item = (XCustomer) unmarshaller.unmarshal(file.toFile());
            items.add(XCustomer.mapToCustomer(item, customerManager, movieManager));
        }
        return items;
    }

    public Vector<Rental> getRentals(CustomerManager customerManager, MovieManager movieManager) throws JAXBException, IOException {
        Vector<Rental> items = new Vector<>();
        JAXBContext context = JAXBContext.newInstance(XRental.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Vector<Path> files = FileManager.listFiles(String.format("%s/%s", XML_PATH, XRental.HOME));
        for (Path file : files) {
            XRental item = (XRental) unmarshaller.unmarshal(file.toFile());
            items.add(XRental.mapToRental(customerManager, movieManager, item));
        }
        return items;
    }
}