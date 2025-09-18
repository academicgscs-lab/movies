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
    public String name;

    @XmlElement
    @Getter
    @Setter
    public double priceCode;

    public XMovie() {}

    public XMovie(String id, String name, double priceCode) {
        this.id = id;
        this.name = name;
        this.priceCode = priceCode;
    }

    public static XMovie mapToXMovie(Movie movie) {
        return new XMovie(movie.id(), movie.title(), movie.priceCode());
    }

}
