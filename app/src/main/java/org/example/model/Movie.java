package org.example.model;

import org.example.utils.Printable;

import java.util.Objects;

public record Movie(String id, String title, int priceCode) implements Printable {
    // TODO: move to config file
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    public static final int CHILDREN = 2;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Movie movie = (Movie) obj;
        return Objects.equals(id, movie.id);
    }

    @Override
    public String getTitle() {
        return id;
    }

    @Override
    public String getBody() {
        return String.format("Title: %s\n Price code: %d\n\r", title, priceCode);
    }
}
