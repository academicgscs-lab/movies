package org.example.persistence.xml.model;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;
import org.example.model.Movie;

@XmlRootElement(name = "movie")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMovie {
    public static String HOME = "movies";

    @XmlElement
    @Getter
    @Setter
    public String id;

    @XmlElement
    @Getter
    @Setter
    public String title;

    @XmlElement
    @Getter
    @Setter
    public int priceCode;

    public XMovie() {}

    public XMovie(String id, String title, int priceCode) {
        this.id = id;
        this.title = title;
        this.priceCode = priceCode;
    }

    public static XMovie mapToXMovie(Movie movie) {
        return new XMovie(movie.id(), movie.title(), movie.priceCode());
    }

    public static Movie mapToMovie(XMovie xMovie) {
        return new Movie(xMovie.getId(), xMovie.getTitle(), xMovie.getPriceCode());
    }

}
