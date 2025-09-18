package org.example.handler.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.example.Customer;
import org.example.handler.xml.model.XMovie;
import org.example.handler.xml.model.XCustomer;

import java.io.File;
import java.io.IOException;

public class XmlHandler {
    public static String XML_PATH = "./xml-storage/";

    public void marshal(Customer customer) throws JAXBException, IOException {
        XMovie movie = new XMovie("Banana", 2);
        JAXBContext context = JAXBContext.newInstance(XMovie.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        marshaller.marshal(movie, createFile("thisATest"));
    }

    private File createFile(String fileName) {
        return new File(String.format("%s/%s.xml", XML_PATH, fileName));
    }
}
