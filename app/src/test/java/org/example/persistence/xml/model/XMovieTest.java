package org.example.persistence.xml.model;

import jakarta.xml.bind.JAXBException;
import org.example.managers.StoreManager;
import org.example.managers.model.MovieManager;
import org.example.model.Movie;
import org.example.persistence.xml.XmlHandler;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class XMovieTest {
    @Test
    void loadMovieTest() throws JAXBException, IOException {
        StoreManager storeManager = new StoreManager();
        MovieManager movieManager = storeManager.getMovieManager();
        Movie createdSoul = storeManager.createMovie("Soul", 2);

        XmlHandler xmlHandler = new XmlHandler();
        xmlHandler.persist(movieManager);

        Vector<Movie> movies = xmlHandler.loadMovies();
        assertTrue(movies.contains(createdSoul));

        Movie loadedSoul = null;
        for (Movie movie : movies) {
            if (movie.equals(createdSoul)){
                loadedSoul = movie;
            }
        }
        if (loadedSoul == null) {
            fail();
        }

        assertEquals(createdSoul.id(), loadedSoul.id());
        assertEquals(createdSoul.title(), loadedSoul.title());
        assertEquals(createdSoul.priceCode(), loadedSoul.priceCode());
    }
}