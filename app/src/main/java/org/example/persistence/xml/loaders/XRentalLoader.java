package org.example.persistence.xml.loaders;

import jakarta.xml.bind.JAXBException;
import org.example.managers.model.CustomerManager;
import org.example.managers.model.MovieManager;
import org.example.model.Rental;
import org.example.persistence.xml.model.XRental;
import org.example.persistence.xml.utils.XmlHelper;
import org.example.utils.FileManager;

import java.nio.file.Path;
import java.util.Optional;
import java.util.Vector;

import static org.example.persistence.xml.XmlStorageHandler.XML_PATH;

public class XRentalLoader implements Loadable<Rental> {
    private final XmlHelper<XRental> helper;
    private final CustomerManager customerManager;
    private final MovieManager movieManager;

    public XRentalLoader(CustomerManager customerManager, MovieManager movieManager) {
        this.customerManager = customerManager;
        this.movieManager = movieManager;
        helper = new XmlHelper<>(XRental.class);
    }

    @Override
    public Optional<Vector<Rental>> load() {
        Vector<Rental> items = new Vector<>();
        Optional<Vector<Path>> files = FileManager.listFiles(String.format("%s/%s", XML_PATH, XRental.HOME));
        if (files.isPresent()) {
            try {
                for (Path file : files.get()) {
                    XRental item = helper.unmarshal(file);
                    Rental rental = XRental.mapToRental(customerManager, movieManager, item);
                    items.add(rental);
                    rental.getCustomer().getRentals().add(rental);
                }
                return Optional.of(items);
            } catch (JAXBException e) {
                System.out.println(e.getMessage());
            }
        }
        return Optional.empty();
    }
}
