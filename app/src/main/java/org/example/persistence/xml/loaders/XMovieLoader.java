package org.example.persistence.xml.loaders;

import jakarta.xml.bind.JAXBException;
import org.example.managers.model.MovieManager;
import org.example.model.Movie;
import org.example.persistence.xml.model.XMovie;
import org.example.persistence.xml.utils.XmlFacade;
import org.example.utils.FileManager;

import java.nio.file.Path;
import java.util.Optional;
import java.util.Vector;

import static org.example.persistence.xml.XmlStorageHandler.XML_PATH;


public class XMovieLoader implements Loadable<Movie>, Marshallable {
    private final XmlFacade<XMovie> helper = new XmlFacade<>(XMovie.class);
    private final MovieManager movieManager;

    public XMovieLoader(MovieManager movieManager) {
        this.movieManager = movieManager;
    }

    @Override
    public Optional<Vector<Movie>> load() {
        Vector<Movie> items = new Vector<>();
        Optional<Vector<Path>> files = FileManager.listFiles(String.format("%s/%s", XML_PATH, XMovie.HOME));
        if (files.isPresent()) {
            try {
                for (Path file : files.get()) {
                    XMovie item = helper.unmarshal(file);
                    Movie movie = XMovie.mapToMovie(item);
                    items.add(movie);
                    movieManager.addItem(item.getId(), movie);
                }
                return Optional.of(items);
            } catch (JAXBException e) {
                System.out.println(e.getMessage());
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean store() {
        boolean result = true;
        for (Movie item : movieManager.getItems().values()) {
            try {
                helper.marshall(XMovie.mapToXMovie(item), FileManager.createFile(XMovie.HOME, item.id()));
            } catch (JAXBException e) {
                System.out.println(e.getMessage());
                result = false;
            }
        }
        return result;
    }
}
