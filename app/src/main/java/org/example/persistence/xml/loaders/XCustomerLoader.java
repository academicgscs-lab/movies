package org.example.persistence.xml.loaders;

import jakarta.xml.bind.JAXBException;
import org.example.managers.model.CustomerManager;
import org.example.model.Customer;
import org.example.persistence.xml.model.XCustomer;
import org.example.persistence.xml.utils.XmlHelper;
import org.example.utils.FileManager;

import java.nio.file.Path;
import java.util.Optional;
import java.util.Vector;

import static org.example.persistence.xml.XmlStorageHandler.XML_PATH;

public class XCustomerLoader implements Loadable<Customer> {
    private final XmlHelper<XCustomer> helper = new XmlHelper<>(XCustomer.class);
    private final CustomerManager customerManager;

    public XCustomerLoader(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    @Override
    public Optional<Vector<Customer>> load() {
        Vector<Customer> items = new Vector<>();
        Optional<Vector<Path>> files = FileManager.listFiles(String.format("%s/%s", XML_PATH, XCustomer.HOME));
        if (files.isPresent()) {
            try {
                for (Path file : files.get()) {
                    XCustomer item = helper.unmarshal(file);
                    Customer customer = XCustomer.mapToCustomer(item);
                    items.add(customer);
                    customerManager.addItem(customer.getId(), customer);
                }
                return Optional.of(items);
            } catch (JAXBException e) {
                System.out.println(e.getMessage());
            }
        }
        return Optional.empty();
    }
}
