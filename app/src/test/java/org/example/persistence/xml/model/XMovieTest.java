package org.example.persistence.xml.model;

import jakarta.xml.bind.JAXBException;
import org.example.managers.StoreManager;
import org.example.model.Movie;
import org.example.persistence.StorageHandler;
import org.example.persistence.xml.XmlStorageHandler;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class XMovieTest {
    @Test
    void loadMovieTest() throws JAXBException, IOException {
        StoreManager storeManager = new StoreManager();
        Movie createdSoul = storeManager.createMovie("Soul", 2);

        StorageHandler handler = new XmlStorageHandler();
        handler.load(storeManager);
        Collection<Movie> movies = storeManager.getMovieManager().getItems().values();

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