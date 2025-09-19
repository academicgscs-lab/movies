package org.example.persistence.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
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

import java.io.IOException;
import java.nio.file.Path;
import java.util.Vector;

// TODO: refactor to use a creational design pattern
class Loader {
    public static String XML_PATH = String.format("%s/persistence/xml/", App.LOCAL_PATH);

    public Vector<Movie> loadMovies() throws JAXBException, IOException {
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

    public Vector<Customer> loadCustomers() throws JAXBException, IOException {
        Vector<Customer> items = new Vector<>();
        JAXBContext context = JAXBContext.newInstance(XCustomer.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Vector<Path> files = FileManager.listFiles(String.format("%s/%s", XML_PATH, XCustomer.HOME));
        for (Path file : files) {
            XCustomer item = (XCustomer) unmarshaller.unmarshal(file.toFile());
            items.add(XCustomer.mapToCustomer(item));
        }
        return items;
    }

    public Vector<Rental> loadRentals(CustomerManager customerManager, MovieManager movieManager) throws JAXBException, IOException {
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

    public void populate(CustomerManager customerManager, MovieManager movieManager)  {
        try {
            loadMovies().forEach(movie -> movieManager.addItem(movie.id(), movie));
            loadCustomers().forEach(item -> customerManager.addItem(item.getId(), item));
            Vector<Rental> rentals = loadRentals(customerManager, movieManager);
            for (Rental rental : rentals) {
                rental.getCustomer().getRentals().add(rental);
            }
        }catch (JAXBException e) {
            System.out.println("Error while populating"+e.getMessage());
        } catch (IOException e) {
            System.out.println("Path not found"+e.getMessage());
        }
    }
}